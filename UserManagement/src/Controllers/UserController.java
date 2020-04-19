package Controllers;


import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import Beans.SingleResponse;
import Beans.User;
import Services.UserService;

@Path("user")
public class UserController {

	@Path("signup")
	@POST
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SingleResponse All(User user) throws Exception {
		 try {
			 UserService.signUp(user);
			return SingleResponse.ok("Success");
		} catch (Exception e) {
			return SingleResponse.error(e);
		}
	}
	@Path("delete/{id}")
	@DELETE
	@RolesAllowed("ROLE_ADMIN")
	@Produces(MediaType.APPLICATION_JSON)
	public SingleResponse delete(@PathParam("id") Long id) {
		try {
			UserService.delete(id);
			return SingleResponse.ok("Success");
		} catch (Exception e) {
			return SingleResponse.error(e);
		}
	}

	@PathParam("update/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed("ROLE_ADMIN")
	@Produces(MediaType.APPLICATION_JSON)
	public SingleResponse update(@PathParam("id") Long id, User a) {
		try {
			UserService.update(a, id);
			return SingleResponse.ok("Success");
		} catch (Exception e) {
			return SingleResponse.error(e);
		}
	}
}

