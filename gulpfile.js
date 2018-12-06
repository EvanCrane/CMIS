const gulp = require('gulp');
const minify = require('gulp-minify');
const cleanCSS = require('gulp-clean-css');
const sourcemaps = require('gulp-sourcemaps');
const rename = require('gulp-rename');
 
gulp.task('minify-js', function() {
  gulp.src('../CMIS/cmis/src/main/resources/static/scripts/*.js')
    .pipe(minify({
        ext:{
            src:'.js',
            min:'-min.js'
        },
        exclude: ['tasks'],
        ignoreFiles: ['.combo.js', '-min.js']
    }))
    .pipe(gulp.dest('../CMIS/cmis/src/main/resources/static/scripts/dist/'));
});

gulp.task('minify', function () {
    gulp.src('../CMIS/cmis/src/main/resources/static/css/*.css')
        .pipe(minify({keepBreaks: true}))
        .pipe(rename({
            suffix: '-min'
        }))
        .pipe(gulp.dest('../CMIS/cmis/src/main/resources/static/css/dist/'))
    ;
});
