package controllers;

import models.Section;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Sections extends Controller {
	
	static Form<Section> sectionForm = Form.form(Section.class);

	public static Result index() {
		return redirect(routes.Application.tasks());
	}
	
	public static Result newSection() {
		Form<Section> filledForm = sectionForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return redirect(routes.Application.tasks());
		} else {
			Section.create(filledForm.get());
			return redirect(routes.Application.tasks());
		}
	}
	
	public static Result deleteSection(Long id) {
		Section.delete(id);
		return redirect(routes.Application.tasks());
	}
	
}
