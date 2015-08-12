(ns bingalot.core
  (:require    [twitter.oauth :as oauth]
               [twitter.callbacks]
               [twitter.callbacks.handlers]
               [twitter.api.restful :as rest]))

(defn get-required-env [name]
  (or (System/getenv name) (throw (RuntimeException. (str "please define " name)))))

(defn authenticate []  (oauth/make-oauth-creds
                        (get-required-env "TWITTER_CONSUMER_KEY")
                        (get-required-env "TWITTER_CONSUMER_SECRET")
                        (get-required-env "TWITTER_ACCESS_TOKEN")
                        (get-required-env "TWITTER_ACCESS_SECRET") ))

(defn -main [say-what]
  (let [creds (authenticate)
        tweet-this (or say-what "Bing!")]
       (rest/statuses-update :oauth-creds creds
                             :params {:status tweet-this})
       (println "We did it! (maybe)"))
  (shutdown-agents))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
