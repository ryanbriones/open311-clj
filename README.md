# open311-clj

A clojure library for connecting to HTTP APIs that implement the Open311
GeoReport v2 specification.

## Usage

```clojure
; http://wiki.open311.org/GeoReport_v2#GET_Service_List
(def random-service (rand-nth
  (open311-clj.core/services
    {:endpoint "http://test311api.cityofchicago.org/open311/v2"})))

; http://wiki.open311.org/GeoReport_v2#GET_Service_Definition
(open311-clj.core/service-definition (:service_code random-service)
  {:endpoint "http://test311api.cityofchicago.org/open311/v2"})

; http://wiki.open311.org/GeoReport_v2#GET_Service_Requests
(def random-request (rand-nth
  (open311-clj.core/requests
    {:endpoint "http://test311api.cityofchicago.org/open311/v2"})))

; http://wiki.open311.org/GeoReport_v2#GET_Service_Requests with params
; see specification for "Optional Arguments" that can be included
; e.g. "all open requests"
(def random-open-request (rand-nth
  (open311-clj.core/requests
    {:endpoint "http://test311api.cityofchicago.org/open311/v2"
     :params {:status "open"} })))

; http://wiki.open311.org/GeoReport_v2#GET_Service_Request
(open311-clj.core/request (:service_request_id random-request)
  {:endpoint "http://test311api.cityofchicago.org/open311/v2"})
```

## TODO

* functions for `POST`ing requests retrieving `service_request_ids` from tokens
* TESTS!!! (My first clojure library, so have mercy)
* docstrings
* clojars (for ease of use)
* `(requests-lazy)` - lazy sequence to "infinitely" iterate over requests pages

## License

MIT License. See LICENSE