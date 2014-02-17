
jgameoflife
===========

[Conway's game of life](http://en.wikipedia.org/wiki/Conway's_Game_of_Life) 
implemented in Java using [Piccolo2D](http://www.piccolo2d.org) to create a
zoomable UI.

Manually step-through generations of the game, and (optionally) toggle the 
state of any node by clicking on it.

The game is implemented as a toroidal array (ie. running off the right edge 
will wrap around to the left edge, same with the top and bottom edges, and 
vice versa), rather than considering cells outside of the array bounds as 
dead.

## Usage ##

1. Get the source

    ```
    $ git clone https://github.com/cahna/jgameoflife-piccolo2d.git
    $ cd jgameoflife-piccolo2d
    ```

2. Compile with Maven

    ```
    $ mvn compile
    ```

3. Run

    ```
    mvn exec:java
    ```

## Dependencies ##

- [Maven](http://maven.apache.org/)
- [Piccolo2D](http://www.piccolo2d.org/) 3.0
    * [piccolo2d-core](http://search.maven.org/#artifactdetails%7Corg.piccolo2d%7Cpiccolo2d-core%7C3.0%7Cbundle)
    * [piccolo2d-extras](http://search.maven.org/#artifactdetails%7Corg.piccolo2d%7Cpiccolo2d-extras%7C3.0%7Cbundle)
- [exec-maven-plugin](http://search.maven.org/#artifactdetails%7Corg.codehaus.mojo%7Cexec-maven-plugin%7C1.2.1%7Cmaven-plugin) 1.2.1
- [JUnit](http://junit.org/) 4.11

## License ##

```
The MIT License (MIT)

Copyright (c) 2014 Conor Heine

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

