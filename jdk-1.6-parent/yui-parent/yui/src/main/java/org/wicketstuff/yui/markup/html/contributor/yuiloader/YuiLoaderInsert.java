package org.wicketstuff.yui.markup.html.contributor.yuiloader;

import org.apache.wicket.Application;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.markup.html.IHeaderResponse;

/**
 * Using YuiLoader Insert method to load all modules required files
 * 
 * @author josh
 * 
 */
public class YuiLoaderInsert extends YuiLoader
{
	private static final long serialVersionUID = 1L;

	public void renderHead(IHeaderResponse response)
	{
		super.renderHead(response);

		StringBuffer bufy = new StringBuffer();
		bufy.append("\n").append("( function() { ");
		bufy.append("\n").append(
				"var loader = new YAHOO.util.YUILoader({" + getYuiLoaderOverride() + "});");
		bufy.append("\n").append(getAddModuleJS("loader"));

		if (Application.DEVELOPMENT.equals(Application.get().getConfigurationType()))
		{
			bufy.append("\n").append("loader.filter=" + DEBUG + ";");
		}
		bufy.append("loader.base = '" + (String)RequestCycle.get().urlFor(BASE) + "';");
		bufy.append("loader.combine = false;");
		bufy.append("loader.onSuccess = " + getOnSuccessJS() + ";");
		bufy.append("loader.insert();");
		bufy.append("\n").append("})();");

		String id = null;
		response.renderJavascript(bufy.toString(), id);
		rendered = true;
	}

	private String getYuiLoaderOverride()
	{
		StringBuffer bufy = new StringBuffer();
		bufy.append("\n").append("require:").append(getRequireModule());
		return bufy.toString();
	}

	protected String getOnSuccessJS()
	{
		StringBuffer bufy = new StringBuffer();
		bufy.append("\n").append("function(o) { ");
		bufy.append("\n").append(allOnSuccessJS());
		bufy.append("\n").append("}");
		return bufy.toString();
	}

	@Override
	protected YuiLoader newLoader()
	{
		return new YuiLoaderInsert();
	}
}
