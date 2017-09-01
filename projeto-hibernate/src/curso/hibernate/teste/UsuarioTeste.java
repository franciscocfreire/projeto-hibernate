package curso.hibernate.teste;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import curso.hibernate.modelo.Endereco;
import curso.hibernate.modelo.EstadoCivil;
import curso.hibernate.modelo.Usuario;

public class UsuarioTeste {

	public static void main(String[] args) {

		Usuario usuario = new Usuario();
		// usuario.setId(2);
		usuario.setNome("Usuario Teste 1");
		usuario.setDataNascimento(new Date());
		usuario.setIdade(32);
		usuario.setEstadoCivil(EstadoCivil.SOLTEIRO);
		Endereco endereco = new Endereco();
		endereco.setCidade("São Paulo");
		endereco.setLogradouro("Rua Dom Constantino Barradas");
		endereco.setNumero(45);
		usuario.setEndereco(endereco);

		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure().build();

		try {
			// SessionFactory factory = new Configuration().configure()
			// .buildSessionFactory();

			SessionFactory factory = new MetadataSources(registry)
					.buildMetadata().buildSessionFactory();
			Session session = factory.openSession();
			session.beginTransaction();

			session.save(usuario);
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

}
