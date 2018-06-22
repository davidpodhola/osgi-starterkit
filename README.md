# OSGi Starter-Kit with H2 and JavaScript integration tests
Bare minimum starting point for OSGi based web apps also with [H2 database](www.h2database.com) provider and [chakram](http://dareid.github.io/chakram/) JavaScript REST API integration tests.

## Quick start
0. Install all the prerequisities, mainly [Gradle](https://gradle.org/)
1. `git clone` this repo then `gradle syncLibs`;
2. `gradle build` to compile the provided sample subprojects; The resulting jars are copied to `runtime/bundle/hot-deploy/subprojects` folder.
3. in a separate terminal `./start.sh` to start the OSGi Runtime;
4. `(cd integration_tests; npm i)` to install the integration tests dependencies
4. `(cd integration_tests; ./run.sh)` to run the integration tests

## Create you own subproject
Read more in [the detailed documentation](DETAILS.md).

## License
Copyright 2017 Tim Lauv. Copyright 2018 David Podhola. 
Under the [MIT](http://opensource.org/licenses/MIT) License.
