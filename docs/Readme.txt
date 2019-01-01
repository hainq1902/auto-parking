Title
Calculate parking fee for M1, M2 and M3 zone

URL
/calculate/:zone?from=:from&to=:to

Method
GET

URL Params
:from       from date using org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME format
:to         to date using org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME format

Required:
zone=[string]
example: M1

from=[DateTime]
example: 2018-12-22T21:34:55

to=[DateTime]
example: 2018-12-23T21:34:55

Success Response
Return JSON with following info: zone, from and to.

Example:
Code: 200
Content: {"zone":"M1","from":"2018-12-22T21:34:55","to":"2018-12-23T21:34:55","fee":1440.0}

Error Response
In case zone is not supported.

Example:
Code: 400 BAD_REQUEST
Content: :zone is not supported

Sample Call
GET http://localhost:8080/calculate/M1?from=2018-12-22T21:34:55&to=2018-12-23T21:34:55
Accept: application/json
Cache-Control: no-cache

Notes
This is for demo purpose only.