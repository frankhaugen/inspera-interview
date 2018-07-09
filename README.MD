## Documentation
See further down for the complete assignment.

This readme has been used to write notes and keep track of the assignment.

### Summary
This task seemed to be easy, as I just had to learn: a new IDE and tools, programming language, data format (json) and Unit Testing; a couple of hours and "boom" I'd be finished. So two weeks later, (though only two work-days in actual effective working time), I'm a bit more humbled. Because learning is a goal onto itself, this assignment has been very rewarding, and I want to make it better then it is, so I just might edit this code after I have turned it in for review.

The biggest challenges was finding a way to compare json objects. Camparing objects is something that I'm familiar with from C# and Powershell, but Java is more limited, (maybe there are some nice libraries I haven't found yet). Learning Java through a complex problem is a half-way decent way of doing it, but it's really not that big of a difference from PHP and C#. If i was a Python or VB.net -programmer, I'd be flailing about

Turns out that my idea of first creating a program, then demonstrating Unit Testing afterwards, really dosen't work very well. When I learned myself Unit Testing, (basic concepts), I really appreaciated how it works

My previous experiences has been mostly top-down development, (when working on game development in particular), but I did try to implement a more extensible ad maintainable code

### Assignment
1. Take two JSONObjects as input and return a JSONObject that details the changes between the two
2. Change the datetime in the output to Oslo timezone
3. Implement unit testing
4. Document the code
5. Focus on readability and maintainability
6. Add "reviewer" to GitHub repository

### Assumptions 
**Readability** - I've focused on human-readable naming and lot's of comments and assembled a javadoc\ 
**Assistance** - The abilities of a programmer is proportional to their ability to google, read documentation and use any trick necessary to overcome a challenge. I have done all of this to get this assigment done in a timely and meaningful manner. Including calling a friend to get some feedback on my json-evaluating code, resulting in a major re-write\
**Timezone** - Due to lack of flexibility in the assignment's use of the timezone specification, "For time fields the timezone should be CEST (Oslo - UTC+2)", I decided to use a bit more flexible standard, (given that there are two timezones for Oslo depending of the time of year. This required to make some changes to the "production" diff.json-file, so the testing would function properly\
**JSON** - Json conversion to a string will be outputted as the system sees fit, so I assumed that the order compared to the text in the diff.json was unimportant

### Project alterations
org.json - Changed from existing json library\ 
JDK - Switched to 1.8 to get access to modern tools like lambda\ 
JsonAssert - Added for easier Unit Testing\ 

### My thought processes
#### Documentation
I am documenting this in the way I've been familiar with the last few years, and is more of a freeform method, rather than a documentation framework/standard. Working within govenrment bounderies, the focus was to write as much as possible and not worry about form

#### JSON Parsing
This is the first time I'm really working with json, and I find it to be somewhat unintuitive compared to XML, (which matches my thought pattern better), so I have done the best I can based on my experience with nested data\
First I started working with the recommended json-library, but I changed over to org.json. Working with json-objects, its clear that this task is incredibly complex. Given my own approach to problem-solving, I reduced the complexity, and "flattened" the data. A simpler data-set should give more possibilities. This might reduce the finesse of the solution but solving a problem is what it's all about\
After wasting a couple of hours working with flattened data, I reverted strategy back to working directly with json, as my strategy whent from making something flexible, to something more specific to solv this assignment
#### Time Zones
Time zones is in theory not that hard, but I spent a lot of time getting to know the the library, before finding a smooth solution. The solution takes the actual time-zone and not a specified number of hours to offset, (making future adaptions easier)
#### Unit Testing
This is something I'm struggeling to implement in a meaningful manner in this type of project, but I'm working on getting comfortable with the logic behind it. I feel that this project isn't well-suited for Unit Testing as I'm building the whole thing in one big messy branch
 
### Comments
* The assignment does not give context to the json-data, or the assignment as a whole, (lacks a business-case to frame the need that is to be fulfilled)
* The suggested JSON library was confusingly hard to find documentation for, so I chose to use org.json instead
* Time-Zones SUCK! Java datetime-handling is convoluted, and as unintuitive as it comes
* Was tempted to convert JSON to XML, to work with a more familiar data format, but resisted. JSON is similar, but different enough to cause some frustrations, resulted in a lot of reading
* Finding the best way to work with json-data, (according to the internet), is to use loops to get through it
* Using Git, (GitHub), is a must, but I started off wrong. I'm used to working with projects that only I contribute to, so branching is something I need better routines for. In this project I should have setup the "main" class in "Master" and branched out to a "develop" with "features" under that; attempting to only work within a set of files related to one set of functionality. I have jumped around working on the entire code simultaniously, and not in a tactical mannar to have maximum focus and efficency

### Progress
Type | 27/6 | 28/6 | 29/6 | 30/6 | 1/7 | 2/7 | 3/7 | 4/7 | 5/7 | 6/7 | 7/7 | 8/7
--- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- 
Hours | 2 | 0 | 0 | 0 | 3 | 2 | 2 | 2 | 1 | 1 | 0 | 3

(Maybe doing this in the middle of my summer-plans was a bad idea)  
Total time spent: 15  
A lot of the reading and watching tutorials have not been included\
Also the time spent is guestimates and not stopwatch-accurate, and so it's been rounded up for sake of honesty

### Day-by-day overview
**27/06:** Created a virtual Linux machine in VirtualBox to test IDEs, and toyed around with some code, and libraries. I also watched/read tutorials on the uses of Java IDEs\
**01/07:** I did at reset of the project to "start over" on GitHub. I configured NetBeans on my primary desktop computer. Ending with making a class to extract json text from the json-files "before" and "after". Before this I also spent some time learning Java\
**02/07:** The work on the json-text extraction was expanded, and I read a lot of documentation on json and the org.json library\
**03/07:** Getting tired of json, I tackled timezones. Timezones was somewhat of a hassle due to my complete inexperience with Java\
**04/07:** Worked on the parser using a traditional "flattened" data to compare the data\
**05/07:** Read some more Json documentation and found a better way, (using objects directly), but didn't really get it to work as I wanted\
**06/07:** Finished work on the parser, (there are some issues with the presentation/ordering of some elements\
**08/07:** Finishing up with Unit Testing, beautification, and writing the last documentation

### Resources
[Programming must-see - YouTube](https://www.youtube.com/playlist?list=PLKe2afIXvv-RHCmkIVZ3H3LaKPnugxrmv) - A YouTube playlist I put toghether with videos I found helpful\
[json2table](http://json2table.com/) - Simple tool to visualize json data in a simple table, to easier make assumptions about how to treat the data\
[Java DateTime timezone conversion](https://www.mkyong.com/java/java-convert-date-and-time-between-timezone/) - Tutorial on timezones\
[Steve Ballmer: Developers developers developers developers](https://www.youtube.com/watch?v=Vhh_GeBPOhs) - To feel a bit better\
[Steve Ballmer: Developers developers developers developers!](https://www.youtube.com/watch?v=33grif58qO8) - To feel a lot better, (with music)

### json data
<details><summary>Expand before.json</summary>

```json
{
     "id": 1,
     "meta": {
       "title": "Title",
       "startTime": "2016-01-20T10:00:00Z",
       "endTime": "2016-01-20T16:00:00Z"
     },
     "candidates": [
       {
	 "id": 10,
	 "candidateName": "C1",
	 "extraTime": 0
       },
       {
	 "id": 11,
	 "candidateName": "C2",
	 "extraTime": 10
       },
       {
	 "id": 12,
	 "candidateName": "C3",
	 "extraTime": 20
       }
     ]
   }
```

</details>

<details><summary>Expand after.json</summary>

```json
{
  "id": 1,
  "meta": {
    "title": "New Title",
    "startTime": "2016-01-20T10:00:00Z",
    "endTime": "2016-01-20T18:00:00Z"
  },
  "candidates": [
    {
      "id": 10,
      "candidateName": "C1",
      "extraTime": 10
    },
    {
      "id": 11,
      "candidateName": "C5",
      "extraTime": 10
    },
    {
      "id": 13,
      "candidateName": "C4",
      "extraTime": 0
    }
  ]
}
```

</details>

<details><summary>Expand diff.json</summary>

```json
{
  "meta": [
    {
      "field": "title",
      "before": "Title",
      "after": "New Title"
    },
    {
      "field": "endTime",
      "before": "2016-01-20T18:00:00+02",
      "after": "2016-01-20T20:00:00+02"
    }
  ],
  "candidates": {
    "edited": [
      {
        "id": 10
      },
      {
        "id": 11
      }
    ],
    "added": [
      {
        "id": 13
      }
    ],
    "removed": [
      {
        "id": 12
      }
    ]
  }
}
```


</details>

___
## Assignment

### Goal
The goal of the project is to implement a parser that takes two JSONObjects (net.sf.json.JSONObject, or another similar framework) as input and returns a JSONObject that details the changes between the two input objects.

The focus should be on readability and maintainability of the code, not on performance. The idea is to give us an indication of your coding style.

### Setup
- Install Maven
- Run "mvn install" to download dependencies
- Implement functionality described in "Assignments" below

Feel free to use any external libraries you want, just add them to the .pom file.

### Assignments
- Implement the parser
- The parser should take two JSONObjects as input, and output a new JSONObject on the following format:

```
{
  "meta": [
    // List of the fields updated with before and after values
    // For time fields the timezone should be CEST (Oslo - UTC+2)
    {
      "field": "title",
      "before": "before",
      "after": "after"
    }
  ],
  // Object describing the different updates to the candidates list
  "candidates": {
    // List of candidates that have been edited (using the "id" field as identifier)
    "edited": [
        {
            "id": 0
        }
    ],
    // List of candidates that have been added (using the "id" field as identifier)
    "added": [
        {
            "id": 0
        }
   	],
   	// List of candidates that have been removed (using the "id" field as identifier)
    "removed": [
        {
            "id": 0
        }
    ]
  }
}
```
See test/resources/diff.json for how the report should look. This report is based on the diff between before.json and after.json. The "id" field should be used as an identifier. Make assumptions as needed.

- Implement unit tests
- Document the code, including any assumptions and simplifications you make