# Solution

## Helpful links

[json2table](http://json2table.com) - Simple tool to visualize json data in a simple table, to easier make assumptions about how to treat the data





## json data
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



___
# Assignment

## Goal
The goal of the project is to implement a parser that takes two JSONObjects (net.sf.json.JSONObject, or another similar framework) as input and returns a JSONObject that details the changes between the two input objects.

The focus should be on readability and maintainability of the code, not on performance. The idea is to give us an indication of your coding style.

## Setup
- Install Maven
- Run "mvn install" to download dependencies
- Implement functionality described in "Assignments" below

Feel free to use any external libraries you want, just add them to the .pom file.

## Assignments
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