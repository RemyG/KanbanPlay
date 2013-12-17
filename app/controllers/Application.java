package controllers;

import models.Section;
import models.Task;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	static Form<Task> taskForm = Form.form(Task.class);
	static Form<Section> sectionForm = Form.form(Section.class);

	public static Result index() {
		return redirect(routes.Application.tasks());
	}

	public static Result tasks() {
		return ok(views.html.index.render(Task.all(), taskForm, Section.all(), sectionForm));
	}

	public static Result newTask() {
		Form<Task> filledForm = taskForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.index.render(Task.all(), filledForm, Section.all(), sectionForm));
		} else {
			Task.create(filledForm.get());
			return redirect(routes.Application.tasks());
		}
	}
	
	public static Result updateTask() {
		Form<Task> filledForm = taskForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.index.render(Task.all(), filledForm, Section.all(), sectionForm));
		} else {
			Task newValues = filledForm.get();
			Task task = Task.find.byId(newValues.id);
			task.update(newValues);
			Section newSection = Section.find.byId(newValues.section.id);
			if (newSection.id != task.section.id) {
				task.updateSection(newSection);
			}
			return redirect(routes.Application.tasks());
		}
	}

	public static Result deleteTask(Long id) {
		Task.delete(id);
		return redirect(routes.Application.tasks());
	}
	
	public static Result updateSectionForTask() {
		DynamicForm requestData = Form.form().bindFromRequest();
		Long taskId = Long.parseLong(requestData.get("taskId"));
		Long sectionId = Long.parseLong(requestData.get("sectionId"));
		Task task = Task.find.byId(taskId);
		Section section = Section.find.byId(sectionId);
		task.updateSection(section);
		return ok("Done");
	}

}
