# S13 Exercice 4

> On parle parfois de "one-way function" pour une fonction de hachage, dans le 
sens où il est difficile/impossible de reconstruire un élément à partir de la 
valeur de hachage. Expliquer brièvement l'application de la notion de hachage 
dans les domaines suivants 

## Sécurité des systèmes informatiques,

Password are hashed so we can't reverse-engineer them once in the database.
With that technology, a hacker can inflitrate a database but he can't get the
users passwords. Sometimes, a salt is also added to the password to make the
hash looks different so the "123456" passwords looks different in the db

## Intégrité des données échangées sur un réseau

Used to generate the signature of a data sent data. We send the data and tis
signature/hash and we re-hash the data at arrival. If they differ, we know 
that something went wrong

