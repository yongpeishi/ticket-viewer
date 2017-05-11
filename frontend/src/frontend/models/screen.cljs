(ns frontend.models.screen)

(defn set-loading [state]
  (assoc-in state [:screen] :loading))

(defn set-error-with-status [state status]
  (assoc-in state [:screen] (if (= 404 status)
                              :not-found
                              :something-went-wrong)))
