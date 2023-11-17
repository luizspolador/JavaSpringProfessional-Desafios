package br.com.spolador.desafio01;

import br.com.spolador.desafio01.entities.Order;
import br.com.spolador.desafio01.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
public class Desafio01Application implements CommandLineRunner {
	Scanner sc = new Scanner(System.in);

	@Autowired
	private OrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(Desafio01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("--- DADOS DE ENTRADA ---");
		System.out.print("Informe o código: ");
		Integer code = sc.nextInt();
		System.out.print("Informe valor básico do pedido: ");
		Double basic = sc.nextDouble();
		System.out.print("Informe a porcentagem do desconto: ");
		Double discount = sc.nextDouble();

		Order order = new Order(code, basic, discount);

		System.out.println("--- DADOS SAÍDA ---");
		System.out.print("Pedido código: " + order.getCode());
		System.out.print("Valor total: R$ " + orderService.total(order));

	}
}
