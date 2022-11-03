package projet.converter;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import projet.jsf.data.Enchere;

@Named
@RequestScoped
public class ConverterEnchere implements Converter<Enchere> {

	// Actions

	@SuppressWarnings("unchecked")
	@Override
	public Enchere getAsObject(FacesContext context, UIComponent uic, String value) {

		if (value == null || value.isEmpty()) {
			return null;
		}

		List<Enchere> items = null;
		for (UIComponent c : uic.getChildren()) {
			if (c instanceof UISelectItems) {
				items = (List<Enchere>) ((UISelectItems) c).getValue();
				break;
			}
		}

		var id = Integer.valueOf("x"+value);
		for (Enchere item : items) {
			if (item.getIdenchere().equals(id)) {
				return item;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Enchere item) {

		if (item == null) {
			return "";
		}
		return String.valueOf(item.getIdenchere());
	}
}
