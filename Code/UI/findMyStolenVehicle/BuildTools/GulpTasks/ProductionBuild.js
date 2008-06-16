var gulp = require('gulp');
var usemin = require('gulp-usemin');
var path = require('path');
var uglify = require('gulp-uglify');
gulp.task('copy:content', function(){
    gulp.src(['./src/Associates/**/*', '!**/*.localhost.*'])
        .pipe(gulp.dest('dist/'));
});
gulp.task('usemin',['copy:content','templates','tsc'], function(){
    gulp.src(['./src/associates/**/*.html','./src/associates/**/index.php', '!**/*.localhost.html'])
        .pipe(usemin({
            css: ['concat'],
            js: ['concat', uglify({
                mangle:false,
                compress:false
            })]
        }))
        .pipe(gulp.dest('dist/'));
});