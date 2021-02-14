# LolTeamManager - L'application parfaite pour gérer ses équipes League of Legends

LolTeamManager est une application de gestion d'équipes, elle permet au gérant d'une structure d'ajouter, de supprimer et de modifier des équipes de cinq joueurs. Chaque joueur d'une équipe est chargé depuis l'API Riot Games à condition que le nom entré existe dans leur base de données. Si aucun joueur n'est trouvé depuis le nom donné, alors un joueur vide est créé avec pour nom "No User Found".
Les données de l'application sont sauvegardées en local dans une base de donnée SQLite (via Room), donc si l'application est désinstallée, vous perdrez toutes les données associées.

À terme, l'application permettra de consulter l'historique des parties de chaque joueur d'une équipe par l'intermédiaire d'un bouton "Infos".

Si vous souhaitez tester l'application vous pouvez réaliser ces opérations :

- Au lancement de l'application, appuyez sur le bouton + pour créer une nouvelle équipe
- Entrez les données que vous voulez (si vous n'entrez pas de nom pour l'équipe un message d'erreur s'affichera)
- Une fois que vous avez validé la création, une notification apparaîtra vous prévenant d'un chargement des joueurs depuis l'API
- Si un joueur n'est pas trouvé, l'user "No User Found" est chargé par défaut
- Maintenant que vous avez une équipe créée, vous pouvez soit en créer une nouvelle depuis le bouton +, soit cliquer sur la team créée pour la modifier ou la supprimer
- Si vous fermez l'application et que vous la réouvrez, vous retrouverez toutes vos données précédemment enregistrées !
- Pour l'instant le bouton "Infos" n'exécute pas d'action, mais à terme l'historique du joueur sera disponible (cette fonctionnalité était presque terminée le 14/02/2021)

**Attention !!! Pour utiliser l'application, il vous faut une API Key que je peux vous envoyer par mail, une fois que vous en aurez fait la demande et que vous l'aurez reçu, il faudra aller dans la classe NetworkFragment (package fragment) et remplacer l'ancienne API_KEY par celle reçue (valable 24H).**
