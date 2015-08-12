(ns bingalot.core
  (:require    [twitter.oauth :as oauth]
               [twitter.callbacks]
               [twitter.callbacks.handlers]
               [twitter.api.restful :as rest]))

(defn authenticate []  (oauth/make-oauth-creds
                        (or (System/getenv "TWITTER_TOKEN") (throw (RuntimeException. "please define TWITTER_TOKEN")))
                        (or (System/getenv "TWITTER_SECRET") "carrot") ))

(defn -main []
  (let [creds (authenticate)]
       (rest/statuses-update :oauth-creds creds
                             :params {:status "hello world"})))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
