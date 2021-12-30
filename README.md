# AvaliacaoSprint4

Os endpoints para essa API serão os seguintes:

Associado

	• POST -/associados
	• POST -/associados/partidos (Vincula um associado a um partido, com o body: {“idAssociado”: 10, “idPartido”: 10})
	• GET -/associados  (Ter  uma  opção  de  filtrar  associados  de  acordo  com  seu  cargo  político  e outra de ordenar por nome)
	• GET -/associados/{id}
	• PUT -/associados/{id}
	• DELETE -/associados/{id}
	• DELETE -/associados/{id}/partidos/{id} (Remove determinado associado daquele partido)	

Partido
	
	• POST -/partidos
	• GET -/partidos (Ter uma opção de filtrar partidos de acordo com sua ideologia)
	• GET -/partidos/{id}
	• GET -/partidos/{id}/associados (Listar todos os associados daquele partido)
	• PUT -/partidos/{id}
	• DELETE -/partidos/{id}
