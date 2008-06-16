var gulp = require('gulp');
var typescript = require('gulp-tsc');
var rename = require('gulp-rename');
var foreach = require('gulp-foreach');
var path = require('path');

gulp.task('tsc', ['tsc:common', 'tsc:cards', 'tsc:auth', 'tsc:portal']);

gulp.task('tsc:common', function(){
    return gulp.src('src/Modules/Common/CommonModule.ts')
        .pipe(typescript({
            tmpDir: '.tmp',
            sourcemap: true,
            out: 'CommonModule.js',
            outDir: './src/Modules/Common/'
        }).on('error', function(a){
        // Dont break the watch if compiler errors just emit to console.
        }))
        .pipe(gulp.dest('./src/Modules/Common/'));
});

gulp.task('tsc:cards', function(){
    return gulp.src('src/Modules/Cards/CardsModule.ts')
        .pipe(typescript({
            tmpDir: '.tmp',
            sourcemap: true,
            out: 'CardsModule.js',
            outDir: './src/Modules/Cards/'
        }).on('error', function(a){
            // Dont break the watch if compiler errors just emit to console.
        }))
        .pipe(gulp.dest('./src/Modules/Cards/'));
});

gulp.task('tsc:auth', function(){
    return gulp.src('src/Modules/Auth/**/*.ts')
        .pipe(typescript({
            tmpDir: '.tmp',
            sourcemap: true
        }).on('error', function(a){
            // Dont break the watch if compiler errors just emit to console.
        }))
        .pipe(gulp.dest('./src/Modules/Auth/'));
});

gulp.task('tsc:portal', function(){
    return gulp.src('src/Modules/Portal/PortalModule.ts')
        .pipe(typescript({
            tmpDir: '.tmp',
            sourcemap: true,
            out: 'PortalModule.js',
            outDir: './src/Modules/Portal/'
        }).on('error', function(a){
            // Dont break the watch if compiler errors just emit to console.
        }))
        .pipe(gulp.dest('./src/Modules/Portal/'));
});


gulp.task('watch:tsc', ['watch:tsc:common','watch:tsc:cards','watch:tsc:auth', 'watch:tsc:portal']);
gulp.task('watch:tsc:portal', function(){
    return gulp.watch('src/Modules/Portal/**/*.ts', ['tsc:portal']);
});
gulp.task('watch:tsc:common', function(){
    return gulp.watch('src/Modules/Common/**/*.ts', ['tsc:common']);
});
gulp.task('watch:tsc:cards', function(){
    return gulp.watch('src/Modules/Cards/**/*.ts', ['tsc:cards']);
});
gulp.task('watch:tsc:auth', function(){
    return gulp.watch('src/Modules/Auth/**/*.ts', ['tsc:auth']);
});