var gulp = require('gulp');
var htmlmin = require('gulp-htmlmin');
var concat = require('gulp-concat');
var header = require('gulp-header');
var footer = require('gulp-footer');
var templategen = require('./../TemplateGenerator');
var glob = require('glob');
var path = require('path');
function createFileName(src){
    var n = src.split(path.sep);
    return n[n.length-1].replace('.tpl.html', '').toLowerCase();
}
var merge = require('merge-stream');
gulp.task('templates', function(cb) {
    var _cb = cb;
    var moduleFolders = glob.sync('src/Modules/*/');
    var tasks = moduleFolders.map(function(folder){
        var componentName = folder.match(/.+\/(.+)\/$/)[1].toLowerCase();
        return gulp.src(folder + '/**/*.tpl.html')
            .pipe(htmlmin({collapseWhitespace: true, removeComments: true}))
            .pipe(templategen({filename: createFileName}))
            .pipe(concat(componentName+'.templates.ts'))
            .pipe(header('module ec.' + componentName + '.templates {\n'))
            .pipe(footer('\n}'))
            .pipe(gulp.dest(folder))
    });
    return merge(tasks);
});

gulp.task('watch:tpl', function() {
    return gulp.watch('src/**/*.tpl.html', ['templates']);
});