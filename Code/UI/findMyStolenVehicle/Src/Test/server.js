var express = require('express');
var cors = require('express-cors');
var path = require('path');
var mime = require('mime');
var bodyParser = require('body-parser');
var cookieParser = require('cookie-parser');
var session = require('express-session');
var timespan = require('timespan');
var fs = require('fs');
var app = express();
var middleware = {

    sendFile:function(filePath,req,res){

        var file = "TestData/" + req.session.user.phoneNumber + "/" + filePath;


        var filename =path.basename(file);
        var mimetype = mime.lookup(file);

        res.setHeader('Content-disposition', 'attachment; filename=' + filename);
        res.setHeader('Content-type', mimetype);

        var filestream = fs.createReadStream(file);
        filestream.pipe(res);
    },
    hasSession: function (req, res, next) {
        if (!req.session.user) {
            if (typeof req.query.user !== "undefined") {
                middleware.autoLogin(req, res, next, +req.query.user);
            } else {
                console.log("No session found returning 401");
                res.status(401).end();
            }
        } else {
            next();
        }
    },
    isDirectoryExists:function(path ,req){
        var filePath = "TestData/" + req.session.user.phoneNumber + "/" + path;
        console.log(filePath);
        try{
            fs.lstatSync(filePath).isDirectory();
            return true;
        }catch(e){
            return false;
        }


    },
    haveFreshSession: function (req, res, next) {

            console.log("Validating timespan from last login");
            var ts = timespan.fromDates(req.session.lastLogin, new Date()).totalSeconds();
            if (ts > 10) {
                console.log("Time to long from last request was:" + ts + " must be lower than 10s");
                res.status(417).send({reAuthToken:12345});
            } else {
                console.log("Timespan check ok, time from last login was:" + ts);
                next();
            }

    },
    autoLogin: function (req, res, next, userId) {
        fs.readFile('TestData/' + userId + '/userData/user.json', 'utf8', function (err, data) {
            if (err) {
                res.send(400, {
                    "text": "User not found",
                    "code": "400"
                });
            }
            var userResponse = JSON.parse(data);
            middleware.logIn(req, userResponse.user);
            next();
        });
    },

    saveFiles:function(res,req){

        fs.readFile(req.files.displayImage.path, function (err, data) {

            var newPath = "TestData/" + req.session.user.phoneNumber + "/userData/uploadedFiles/11.txt";
            fs.writeFile(newPath, data, function (err) {
                //res.redirect("back");
            });
        });

    },
    getJson: function (filePath,res,req,status) {
        var file;
        if(req.session.user)
            file = "TestData/" + req.session.user.phoneNumber + "/" + filePath;
        else
            file = "TestData/12345/" + filePath;
        fs.readFile(file, 'utf8', function (err, data) {
            if (err) {
                res.status(404).send({
                    message: "Not found",
                    text: "The data you asked for could not be found",
                    code: 404
                });
                return;
            }
            res.status(status || 200).json(JSON.parse(data));
        });
    },
    loadJson: function(filePath,user, success, error){
        var file;
        file = "TestData/" + user.phoneNumber + "/" + filePath;
        fs.readFile(file, 'utf8', function (err, data) {
            if (err) {
                error(err);
            }
            if(!data){
                success({});
            }
            success(data);
        });
    },
    logIn: function (req, user) {
        if (typeof req.session.user === "undefined") {
            console.log("Creating new session");
            req.session.user = user;
        }
        console.log("Updating 'last login' timestamp in session");
        req.session.lastLogin = new Date();
    },
    lagg: function(req, res, next){
            console.log("Adding 1 sec lagg to this call");
            setTimeout(next, 1000);
    },
    lagg5Sec: function(req, res, next){
        console.log("Adding 5 sec lagg to this call");
        setTimeout(next, 5000);
    },
    simulateTimeout: function(req, res, next){
        console.log("simulate Timeout");
        setTimeout(next, 15000);
    }


};
process.argv.forEach(function (val, index, array) {
    if(val === "-logHeaders"){
        app.use(function(req,res, next){
            if(req.url.indexOf("/api") > -1){
                //console.log({body: req.body, cookies: req.cookies, method: req.method, originalUrl: req.originalUrl, headers: req.headers});
                console.log("############################ START ###################################");
                console.log(req.headers);
                console.log("############################  END  ###################################");
            }
            next();
        });
    }
});

//var appUrl = process.env.appUrl || "http://localhost:8081";
app.use(cors({
    headers: ['X-Requested-With', 'Content-Type', 'ecregion', 'ecgroup','csrftoken'],
    allowedOrigins: [
        'localhost:8080',
        'localhost:8081',
        'localhost:8082',
        'localhost:1337',
        'localhost:63342',
        'darwin-coop.azurewebsites.net'
        // appUrl
    ]
}));
app.use(bodyParser.json());
app.use(cookieParser());
app.use(session({
    name: 'nodesession',
    secret: 'somesecrettokenhere',
    cookie: {
        expires: false,
        httpOnly: true
    },
    resave: true,
    saveUninitialized: true
}));
app.use('/', express.static(__dirname + '/'));
app.use('/ExternalLibs/', express.static('../../../ExternalLibs/'));
app.use('/App/', express.static('../../App/'));


/*

require('./controllers/CardControllers')(app, middleware);
require('./controllers/LoanControllers')(app, middleware);
require('./controllers/UserControllers')(app, middleware);
require('./controllers/InboxController')(app, middleware);
require('./controllers/auth.js')(app, middleware);
require('./controllers/setpin.js')(app, middleware);
*/

app.get('/api/ping', function (req, res) {
    var haveCurrentSession = !!req.session.user;
    res.send({process: process.env, req: {body: req.body, cookies: req.cookies, method: req.method, originalUrl: req.originalUrl, headers: req.headers}});
});
var port = process.env.PORT || 8080;
console.log("Listening on port:" + port);

app.listen(port);
