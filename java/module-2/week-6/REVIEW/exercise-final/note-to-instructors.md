# Week 6 Review: National Park Service API - instructor notes

This exercise has the students work in Postman and make requests to two different APIs. Included with this file is a Postman collection with the completed API requests that you can use for reference. The Postman collection includes two folders:

* **National Park API** contains the expected requests to get the answers to the exercise challenges.
* **Exercise API** contains the requests for challenges and each expected answer.

To use it, you need to register for a free API key from the National Park Service at https://www.nps.gov/subjects/developer/get-started.htm and add it to the Postman collection. The student `README.md` walks through adding the API key to a Postman collection in the **Authorization** tab. You need to do that, as well as the `npsApiKey` entry on the **Variables** tab—this is for sending it with the Exercise API. The students aren't provided any instructions in this exercise on using variables, this is just to make the Postman collection easier for the instructor to run.

Be aware that there is a small possibility that values from the NPS API could change. When a student submits a request to the Exercise API, it makes a request to the NPS API to get the current value to validate the answer. The requests in the instructor Postman collection are hard-coded, so if one of the requests in **Exercise API** doesn't succeed, use the requests in **National Park API** to check if the expected value has changed.
