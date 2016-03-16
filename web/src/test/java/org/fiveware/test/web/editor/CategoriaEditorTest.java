package org.fiveware.test.web.editor;

import org.fiveware.test.model.entity.Categoria;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoriaEditorTest {

	private CategoriaEditor editor = new CategoriaEditor();
	
	@Test
	public void setAsTextTest() {
		
		editor.setAsText(null);
		editor.setAsText("1");
		
		Categoria cat = (Categoria) editor.getValue();
		
		Assert.assertEquals(1, cat.getId().intValue());
	}
	
	@Test
	public void getAsTextTest() {
		
		Assert.assertNull(editor.getAsText());

		editor.setAsText("1");
		
		Assert.assertEquals("1", editor.getAsText());
	}
	
}
