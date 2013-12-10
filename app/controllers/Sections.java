package controllers;

import models.Section;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.sections;

public class Sections extends Controller {
	
	static Form<Section> sectionForm = Form.form(Section.class);

	public static Result index() {
		return redirect(routes.Sections.getSections());
	}
	
	public static Result getSections() {
		return ok(sections.render(Section.all(), sectionForm));
	}
	
	public static Result newSection() {
		Form<Section> filledForm = sectionForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(sections.render(Section.all(), filledForm));
		} else {
			Section.create(filledForm.get());
			return redirect(routes.Application.tasks());
		}
	}
	
	public static Result deleteSection(Long id) {
		Section.delete(id);
		return redirect(routes.Sections.getSections());
	}
	
}
