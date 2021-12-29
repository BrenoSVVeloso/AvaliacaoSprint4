package com.avaliacaoSprint4.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import com.avaliacaoSprint4.entity.Associado;
import com.avaliacaoSprint4.entity.Partido;
import com.avaliacaoSprint4.entity.FolderEnum.CargoPolitico;
import com.avaliacaoSprint4.entity.FolderEnum.Ideologia;
import com.avaliacaoSprint4.entity.FolderEnum.Sexo;
import com.avaliacaoSprint4.repository.AssociadoRepository;
import com.avaliacaoSprint4.repository.PartidoRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AvaliacaoSprint4ApplicationTests {

	@Autowired
	private AssociadoRepository associadoRepository;

	@Autowired
	private PartidoRepository partidoRepository;

	@Autowired
	private TestEntityManager em;

	@Test
	public void deveriaVoltarUmAssociadoPeloNome() throws ParseException{
		String nomeAssociado = "Jair Bolsonaro";
		Associado associado = new Associado();
		associado.setNome(nomeAssociado);
		associado.setCargo(CargoPolitico.PRESIDENTE);
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date data = formato.parse("1955-03-21");
		associado.setDATADENASCIMENTO("1955-03-21");
		associado.setSexo(Sexo.MASCULINO);
		associado.setPartido("Partido Liberal");
		em.persist(associado);

		Associado associado2 =  associadoRepository.findByNome(nomeAssociado);

		Assertions.assertNotNull(associado2);

		Assertions.assertEquals(associado2.getNome(), nomeAssociado);
	}

	@Test
	public void deveriaCadastrarUmAssociado(){
		Associado associado = new Associado();
		associado.setNome("Fulano");
		associado.setCargo(CargoPolitico.PRESIDENTE);
		associado.setPartido("Partido Liberal");
		associado.setDATADENASCIMENTO("1999-04-07");
		associado.setSexo(Sexo.MASCULINO);
		em.persist(associado);
		Associado associado2 = associadoRepository.findByNome(associado.getNome());
		assertNotNull(associado2);
		assertEquals(associado.getNome(), associado2.getNome());
		assertEquals(associado.getCargo(), associado2.getCargo());
		assertEquals(associado.getDATADENASCIMENTO(), associado2.getDATADENASCIMENTO());
		assertEquals(associado.getSexo(), associado2.getSexo());
	}

	@Test
	public void deveDeletarUmAssociadoAPartirDoNome(){
		Associado associado = new Associado();
		associado.setNome("Fulano");
		associado.setCargo(CargoPolitico.PREFEITO);
		associado.setDATADENASCIMENTO("1999-04-07");
		associado.setPartido("Partido Liberal");
		associado.setSexo(Sexo.MASCULINO);
		em.persist(associado);
		associadoRepository.deleteByNome(associado.getNome());
		assertNull(associadoRepository.findByNome(associado.getNome()));
	}

	@Test
	public void deveriaListarAssociadosPeloCargoPoliticoPrefeito(){
		cadastraAssociados();

		List<Associado> associados = associadoRepository.findByCargo(CargoPolitico.PREFEITO);

		assertNotNull(associados);

	}

	@Test
	public void deveriaExcluirAssociadosPeloCargoPoliticoPrefeito(){
		cadastraAssociados();

		associadoRepository.deleteByCargo(CargoPolitico.PREFEITO);
		assertEquals(associadoRepository.findByCargo(CargoPolitico.PREFEITO), Collections.EMPTY_LIST);
	}


	@Test
	public void deveriaAtualizarAssociados(){
		Associado associado = new Associado();
		associado.setNome("Fulano");
		associado.setCargo(CargoPolitico.PREFEITO);
		associado.setDATADENASCIMENTO("1999-04-07");
		associado.setPartido("Partido Liberal");
		associado.setSexo(Sexo.MASCULINO);

		em.persist(associado);

		Associado associado2 = new Associado();
		associado2.setNome("Fulano");
		associado2.setCargo(CargoPolitico.PREFEITO);
		associado2.setDATADENASCIMENTO("1999-04-07");
		associado2.setPartido("Partido Liberal");
		associado2.setSexo(Sexo.MASCULINO);

		associado.setNome("Adoleta");
		associado.setCargo(CargoPolitico.PRESIDENTE);
		associado.setDATADENASCIMENTO("1999-05-02");
		associado.setPartido("Avante");
		associado.setSexo(Sexo.FEMININO);

		em.persist(associado);

		assertNotEquals(associado.getNome(), associado2.getNome());
		assertNotEquals(associado.getCargo(), associado2.getCargo());
		assertNotEquals(associado.getDATADENASCIMENTO(), associado2.getDATADENASCIMENTO());
		assertNotEquals(associado.getPartido(), associado2.getPartido());
		assertNotEquals(associado.getSexo(), associado2.getSexo());




	}

	private void cadastraAssociados(){
		Associado associado = new Associado();
		associado.setNome("Fulano");
		associado.setCargo(CargoPolitico.PREFEITO);
		associado.setDATADENASCIMENTO("1999-04-07");
		associado.setPartido("Partido Liberal");
		associado.setSexo(Sexo.MASCULINO);

		em.persist(associado);

		Associado associado2 = new Associado();
		associado2.setNome("Ciclano");
		associado2.setCargo(CargoPolitico.PREFEITO);
		associado2.setDATADENASCIMENTO("1999-04-13");
		associado2.setPartido("Avante");
		associado2.setSexo(Sexo.MASCULINO);
		
		em.persist(associado2);

		Associado associado3 = new Associado();
		associado3.setNome("Alano");
		associado3.setCargo(CargoPolitico.PRESIDENTE);
		associado3.setDATADENASCIMENTO("2010-04-07");
		associado3.setPartido("Progressistas");
		associado3.setSexo(Sexo.MASCULINO);
		em.persist(associado3);


		Associado associado4 = new Associado();
		associado4.setNome("Beltrano");
		associado4.setCargo(CargoPolitico.PREFEITO);
		associado4.setDATADENASCIMENTO("1999-12-07");
		associado4.setPartido("Democratas");
		associado4.setSexo(Sexo.MASCULINO);
		em.persist(associado4);
	}

	


	@Test
	public void deveriaCadastrarUmPartido(){
		Partido partido = new Partido();
		partido.setNome("Partido dos fulanos");
		partido.setIdeologia(Ideologia.DIREITA);
		partido.setSigla("VHL");
		partido.setDATAFUNDACAO("1999-04-07");
		em.persist(partido);
		Partido partido2 = partidoRepository.findByNome(partido.getNome());
		assertNotNull(partido2);
		assertEquals(partido.getNome(), partido2.getNome());
		assertEquals(partido.getIdeologia(), partido2.getIdeologia());
		assertEquals(partido.getDATAFUNDACAO(), partido2.getDATAFUNDACAO());
		assertEquals(partido.getSigla(), partido2.getSigla());
	}

	@Test
	public void deveriaVoltarUmPartidoPeloNome() throws ParseException{

		cadastraPartidos();

		Partido partido = partidoRepository.findByNome("Partido Novo");

		assertNotNull(partido.getNome());
	}

	
	@Test
	public void deveDeletarUmPartidoAPartirDoNome(){
		cadastraPartidos();		
		partidoRepository.deleteByNome("Partido Liberal");
		assertNull(partidoRepository.findByNome("Partido Liberal"));
	}

	@Test
	public void deveriaListarPartidosPelaIdeologiaDireita(){
		cadastraPartidos();

		List<Partido> partidos = partidoRepository.findByIdeologia(Ideologia.DIREITA);

		assertNotNull(partidos);
		assertNotEquals(partidos, Collections.EMPTY_LIST);

	}

	
	@Test
	public void deveriaAtualizarPartido(){
		Partido partido = new Partido();
		partido.setNome("Democratas");
		partido.setIdeologia(Ideologia.ESQUERDA);
		partido.setDATAFUNDACAO("1999-03-07");
		partido.setSigla("DE");
		em.persist(partido);

		Partido partido2 = new Partido();
		partido2.setNome("Democratas");
		partido2.setIdeologia(Ideologia.ESQUERDA);
		partido2.setDATAFUNDACAO("1999-03-07");
		partido2.setSigla("DE");

		partido.setNome("Partido Novo");
		partido.setIdeologia(Ideologia.CENTRO);
		partido.setDATAFUNDACAO("2002-04-07");
		partido.setSigla("NOVO");
		em.persist(partido);

		assertNotEquals(partido.getNome(), partido2.getNome());
		assertNotEquals(partido.getIdeologia(), partido2.getIdeologia());
		assertNotEquals(partido.getDATAFUNDACAO(), partido2.getDATAFUNDACAO());
		assertNotEquals(partido.getSigla(), partido2.getSigla());

	}

	@Test
	public void deveriaExcluirPartidosPelaIdeologiaEsquerda(){
		cadastraPartidos();

		partidoRepository.deleteByIdeologia(Ideologia.ESQUERDA);
		assertEquals(partidoRepository.findByIdeologia(Ideologia.ESQUERDA), Collections.EMPTY_LIST);
	}

	private void cadastraPartidos(){

		Partido partido = new Partido();
		partido.setNome("Partido Liberal");
		partido.setIdeologia(Ideologia.DIREITA);
		partido.setDATAFUNDACAO("1999-04-07");
		partido.setSigla("PL");
		em.persist(partido);

		Partido partido2 = new Partido();

		partido2.setNome("Progressistas");
		partido2.setIdeologia(Ideologia.ESQUERDA);
		partido2.setDATAFUNDACAO("1999-04-02");
		partido2.setSigla("PP");
		em.persist(partido2);


		Partido partido3 = new Partido();
		partido3.setNome("Partido Novo");
		partido3.setIdeologia(Ideologia.CENTRO);
		partido3.setDATAFUNDACAO("2002-04-07");
		partido3.setSigla("NOVO");
		em.persist(partido3);

		Partido partido4 = new Partido();

		partido4.setNome("Democratas");
		partido4.setIdeologia(Ideologia.ESQUERDA);
		partido4.setDATAFUNDACAO("1999-03-07");
		partido4.setSigla("DE");
		em.persist(partido4);
	}





}
