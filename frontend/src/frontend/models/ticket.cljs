(ns frontend.models.ticket)

(defn update-ticket [state details]
  (let [result (update-in state [:single] merge details) ]
    (println state)
    (println details)
    (println result)
    result
    ))
