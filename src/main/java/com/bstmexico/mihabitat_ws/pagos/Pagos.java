package com.bstmexico.mihabitat_ws.pagos;

import java.math.BigDecimal;

import mx.openpay.client.Charge;
import mx.openpay.client.Customer;
import mx.openpay.client.core.OpenpayAPI;
import mx.openpay.client.core.requests.transactions.CreateCardChargeParams;
import mx.openpay.client.exceptions.OpenpayServiceException;
import mx.openpay.client.exceptions.ServiceUnavailableException;

public class Pagos {
	
	OpenpayAPI api = new OpenpayAPI("https://sandbox-api.openpay.mx", "sk_a502fa18d86447fdbc155aabe75beb95", "mz8cpzsq1p5nyvv1tyny");
	
	public void GenerarPago(String nombre,String apPaterno , String apMaterno, String email, String token,String monto,String deviceId) {
		CreateCardChargeParams request = new CreateCardChargeParams();
		
		Customer customer = new Customer();
	customer.setName(nombre);
	customer.setLastName(apPaterno+" "+apMaterno);
	customer.setEmail(email);

	request.cardId(token); // =source_id
	request.amount(new BigDecimal(monto));
	request.currency("MXN");
//	request.description("Cargo inicial a mi merchant");
	request.deviceSessionId(deviceId);
	request.customer(customer);

	try {
		Charge charge = api.charges().create(request);
	} catch (OpenpayServiceException | ServiceUnavailableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
