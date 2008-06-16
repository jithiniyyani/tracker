module.exports = function(grunt){


    var initConfig = {
        ts: {
            options: {
                compiler: './BuildTools/TypeScript/bin/tsc.js'
            },

            buildonce: {
                src: ["Src/App/**/*.ts"],
                html: ["Src/App/**/*.html"],
                reference: "Src/App/reference.ts",

                out: 'Src/App/out.js',
                options: {
                    target: 'es5',
                    sourceMap: true,
                    declaration: false,
                    removeComments: false
                }
            }
        },
       copy:{
          testApp: {
              files: [
                {
                    expand: true,
                    cwd: 'Src/App/out.js',
                    src: ['staticContent/*'],
                    dest: 'Src/staticContent/Scripts',
                    filter: 'isFile'
                },
                {
                      expand: true,
                      cwd: 'Src/staticContent',
                      src: ['**'],
                      dest: 'D:/Servers/nginx-1.10.1/html/'
                  }
              ]
          }
        }
    };

    grunt.initConfig(initConfig);
    grunt.loadNpmTasks("grunt-ts");
    grunt.loadNpmTasks('grunt-nodemon');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.registerTask('default', ['ts:buildonce','copy:testApp']);
}
