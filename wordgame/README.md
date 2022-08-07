# WORD GAME

## Technology Used
- Java 11
- Maven 3.8.5
- Lombok 1.18.24
- Junit 5
- Docker

## Requirements

## Run WordGame Command Line
###Go Inside the Project
```cd WordGame```

###Build the Project
```mvn clean compile assembly:single```

###Run the Solution
```bash wordgame.sh "areallylongword"```

## Run WordGame Docker way
###Go Inside the Project
```cd WordGame```

###Build a Image
```docker image build -t wordgame .```

###Run a docker container
```docker container run -it wordgame:latest /bin/bash -c cd /root/WordGame; bash wordgame.sh -s "areallylongword"```


### Footnotes
- Choosing PriorityBlocking Queue Data Structure
  - To manage leaderboard of top 10 players
  - To manage thread safe access to leaderboard
- Exception Handling
  - Extending IndexOutOfBounds Exception class
  - Duplicate wors submission exception
- Improving overall readablity of code and solution