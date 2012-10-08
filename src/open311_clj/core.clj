(ns open311-clj.core
  (:require [clj-http.client :as client]))

(defn- make-request-url [endpoint uri]
  {:url (str endpoint uri)})

(defn- make-request-auth [api-key]
  (cond
    (not (nil? api-key)) {:query-params {:api_key api-key}}
    :else {}))

(defn- make-request-params [params]
  (cond
    (not (nil? params)) {:query-params params}
    :else {}))

(defn- make-request [method uri {:keys [endpoint api-key params]}]
  (merge-with merge {:method method :as :json}
                    (make-request-url endpoint uri)
                    (make-request-auth api-key)
                    (make-request-params params)))

(defn services [options]
  ((client/request (make-request :get "/services.json" options)) :body))

(defn- make-service-definition-uri [service-code]
  (str "/services/" service-code ".json"))

(defn service-definition [service-code options]
  (let [service-definition-uri (make-service-definition-uri service-code)]
    ((client/request (make-request :get service-definition-uri options)) :body)))

(defn requests [options]
  ((client/request (make-request :get "/requests.json" options)) :body))

(defn- make-get-request-uri [service-request-id]
  (str "/requests/" service-request-id ".json"))

(defn request [service-request-id options]
  (let [service-request-uri (make-get-request-uri service-request-id)]
    ((client/request (make-request :get service-request-uri options)) :body)))