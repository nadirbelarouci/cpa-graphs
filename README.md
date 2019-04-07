# CPA Project
### Nadir Belarouci: 3704056
### Date: 8 April 2019
Source files: 
## 1- Handling a large graph

### Size of Graph:
File:  ```SizeOfGraph.java ```

| Graph | Nodes | Edges | 
| ------ | ------ |-------|
| email-Eu-core network | 1005 | 25571 | 
| com-amazon.ungraph.txt |334863|	925872|
| com-lj.ungraph.txt | 3997962|	34681189|
| com-orkut.ungraph.txt|3072441|117185083|
| com-friendster.ungraph.txt|	65608366 | 1806067135|

### Cleaning Data
File:  ```CleanData.java ```
### Node Degree
File:  ```NodeDegree.java ```
### Special Quantity
File: ```SpecialQuantity.java```
#### Machine Infos:
    - Processo: 4 x Intel Xeon 2.2Ghz 
    - Memory: 32 Go 
    - Operating system: Debian 9.8 (x86-64)
    - JVM: OpenJDK Runtime Environment (build 1.8.0_212-8u212-b01-1~deb9u1-b01)

| Graph | Special Quantity | Time | 
| ------ | ------ |-------|
| email-Eu-core network | 383204482 | 215 ms | 
| com-amazon.ungraph.txt |103415531|1s 128ms	|
| com-lj.ungraph.txt | 789000450609|	12s 482ms|
| com-orkut.ungraph.txt|22292678512329|29s 27ms|
| com-friendster.ungraph.txt|	379856554324947 | 11m 47s 222ms|

### Degree distribution 
File; ```Distribution.java```

|  |   | 
| ------ | ---------|
|![email-Eu-core.txt.png](https://www.dropbox.com/s/erp2o46mru5aum4/email-Eu-core.txt.png?dl=0&raw=1)|![com-amazon.ungraph.png](https://www.dropbox.com/s/9biaeyo5pi5q8i8/com-amazon.ungraph.png?dl=0&raw=1) | 
|![com-lj.ungraph.txt.png](https://www.dropbox.com/s/ohk8yd5j36wjxcd/com-lj.ungraph.txt.png?dl=0&raw=1)| ![com-orkut.ungraph.png](https://www.dropbox.com/s/bllp8rk5ncucret/com-orkut.ungraph.png?dl=0&raw=1) |
|![com-friendster.ungraph.png](https://www.dropbox.com/s/g9nnsmlbohu28uv/com-friendster.ungraph.png?dl=0&raw=1)||

### Load Graph in Memory: 
Files:
- ```Graph.java```
-  ```GraphAdjacenyMatrix.java```
- ```GraphAdjacencyList.java```
- ```GraphEdgeList.java```

### Breadth-first search and diameter
Files: 
- ```BFS.java```
- ```ConnectedComponenets.java```

