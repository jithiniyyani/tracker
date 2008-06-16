/// <reference path="node.d.ts"/>
var util = require('util');
var path = require('path');
var chalk = require('chalk');
var gutil = require('gulp-util');
var through = require('through2');

module.exports = function (options) {
    options = options || {};

    function normalizeTemplateName(name) {
        return name.replace('\\', '/').replace('-', '');
    }
    function escapeContent(content) {
        return content.replace(/\\/g, "\\\\").replace(/'/g, "\\'").replace(/\r?\n/g, "\\n' +\n    '");
    }
    function transform(file, encoding, next) {
        if (file.isNull()) {
            return next(null, file);
        }
        var fileName = path.relative(file.base, file.path);
        var contents = escapeContent(file.contents.toString());
        var name = typeof options.filename === 'function' ? options.filename.call(file, fileName) : (options.filename || fileName);
        var content = gutil.template('export var <%= name %>:string = \'<%= contents %>\';', { name: normalizeTemplateName(name), contents: contents, file: '' });
        file.contents = new Buffer(content);
        file.path = path.join(path.dirname(file.path), path.basename(file.path, path.extname(file.path)) + '.js');

        // gutil.log(util.format('File \'%s\' created.', chalk.cyan(path.relative(process.cwd(), file.path))));
        next(null, file);
    }

    return through.obj(transform);
};
