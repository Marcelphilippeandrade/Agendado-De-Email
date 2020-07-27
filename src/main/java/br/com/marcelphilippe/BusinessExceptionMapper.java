package br.com.marcelphilippe;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import br.com.marcelphilippe.dto.MensagemErroDto;
import br.com.marcelphilippe.exception.BusinessException;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

	@Override
	public Response toResponse(BusinessException exception) {
		
		 List<String> mensagens = new ArrayList<>();
		 mensagens.add(exception.getMensagem());
		 return Response
	                .status(Response.Status.BAD_REQUEST)
	                .entity( MensagemErroDto.build(mensagens))
	                .build();
	}

}
