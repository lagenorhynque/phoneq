(ns phoneq.client.db)

(def default-db
  {:view :phoneq.client.views/home
   :input-text nil
   :lang :en-GB
   :config {:en-GB
            {:problems
             ["I'm looking for a book on functional programming."]}
            :fr-FR
            {:problems
             ["Je cherche un livre sur la programmation fonctionnelle."]}
            :it-IT
            {:problems
             ["Sto cercando un libro sulla programmazione funzionale."]}
            :es-ES
            {:problems
             ["Estoy buscando un libro sobre programación funcional."]}
            :de-DE
            {:problems
             ["Ich suche ein Buch über funktionale Programmierung."]}
            :ru-RU
            {:problems
             ["Я ищу книгу о функциональном программировании."]}}})
