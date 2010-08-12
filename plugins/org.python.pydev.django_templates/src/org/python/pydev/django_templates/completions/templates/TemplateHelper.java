package org.python.pydev.django_templates.completions.templates;

import java.io.IOException;

import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.ui.editors.text.templates.ContributionContextTypeRegistry;
import org.eclipse.ui.editors.text.templates.ContributionTemplateStore;
import org.python.pydev.django_templates.DjPlugin;

/**
 * Helper for getting the template store and registry.
 * 
 * @author Fabio
 */
public class TemplateHelper {
    
    /** The template store. */
    private static TemplateStore fStore;

    /** The context type registry. */
    private static ContributionContextTypeRegistry fRegistry;

    /** Key to store custom templates. */
    public static final String CUSTOM_TEMPLATES_DJ_KEY = "dj_custom_templates";

    /**
     * Returns this plug-in's template store.
     * 
     * @return the template store of this plug-in instance
     */
    public static TemplateStore getTemplateStore() {
        if (fStore == null) {
            fStore = new ContributionTemplateStore(TemplateHelper.getContextTypeRegistry(), 
                    DjPlugin.getDefault().getPreferenceStore(), CUSTOM_TEMPLATES_DJ_KEY);
            try {
                fStore.load();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return fStore;
    }

    /**
     * Returns this plug-in's context type registry.
     * 
     * @return the context type registry for this plug-in instance
     */
    public static ContextTypeRegistry getContextTypeRegistry() {
        if (fRegistry == null) {
            // create an configure the contexts available in the template editor
            fRegistry = new ContributionContextTypeRegistry();
            fRegistry.addContextType(DjContextType.DJ_COMPLETIONS_CONTEXT_TYPE);
            fRegistry.addContextType(DjContextType.DJ_TAGS_COMPLETIONS_CONTEXT_TYPE);
            fRegistry.addContextType(DjContextType.DJ_FILTERS_COMPLETIONS_CONTEXT_TYPE);
        }
        return fRegistry;
    }
    
    /**
     * Used from jython scripts.
     */
    public static void clearTemplateRegistryCache(){
    	fRegistry = null;
    }

}