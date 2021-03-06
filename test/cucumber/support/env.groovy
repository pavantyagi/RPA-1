package support

import geb.Browser
import geb.binding.BindingUpdater
import org.codehaus.groovy.grails.test.support.GrailsTestRequestEnvironmentInterceptor
import rpa.ResearchGroup
import rpa.Researcher
import rpa.UpdateLattes
import rpa.Article
import rpa.Qualis


this.metaClass.mixin(cucumber.api.groovy.Hooks)

Before() {
    bindingUpdater = new BindingUpdater(binding, new Browser())
    bindingUpdater.initialize()

    scenarioInterceptor = new GrailsTestRequestEnvironmentInterceptor(appCtx)
    scenarioInterceptor.init()
}

After() {
    ResearchGroup.list().each{
        it.delete(flush: true)
    }

    Researcher.list().each {
        it.delete(flush:true)
    }

    UpdateLattes.list().each {
        it.delete(flush:true)
    }

    Article.list().each {
        it.delete(flush:true)
    }

    Qualis.list().each {
        QualisAvaliation.where{qualis == it}.deleteAll()
        it.delete(flush: true)
    }

    scenarioInterceptor.destroy()

    bindingUpdater.remove()
}