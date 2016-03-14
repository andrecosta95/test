package org.fiveware.test.web.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.fiveware.test.model.entity.Categoria;

public class CategoriaEditor extends PropertyEditorSupport   {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		if(StringUtils.isNotBlank(text)) {
			
			Categoria cat = new Categoria();
			cat.setId(Integer.parseInt(text));
			
			setValue(cat);
		}
	}
	
	@Override
	public String getAsText() {
		
		Categoria cat = (Categoria) getValue();
		if(cat != null) {
			return cat.getId().toString();
		}
		
		return super.getAsText();
	}
	
}
