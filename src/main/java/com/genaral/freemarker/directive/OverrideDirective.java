package com.genaral.freemarker.directive;

import freemarker.core.Environment;
import freemarker.template.*;

import java.io.IOException;
import java.util.Map;

/**
 * @author badqiu
 */
public class OverrideDirective implements TemplateDirectiveModel {

	public final static String DIRECTIVE_NAME = "override";
	
	public void execute(Environment env,
            Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException {
		String name = DirectiveUtils.getRequiredParam(params, "name");
		String overrideVariableName = DirectiveUtils.getOverrideVariableName(name);
		
		if(isOverrieded(env, overrideVariableName)) {
			return;
		}
		
		env.setVariable(overrideVariableName, new TemplateDirectiveBodyModel(body));
	}

	private boolean isOverrieded(Environment env, String overrideVariableName) throws TemplateModelException {
		return env.getVariable(overrideVariableName) != null;
	}
	
	public static class TemplateDirectiveBodyModel implements TemplateModel{
		public TemplateDirectiveBody body;
		public TemplateDirectiveBodyModel(TemplateDirectiveBody body) {
			this.body = body;
		}
	}
}
