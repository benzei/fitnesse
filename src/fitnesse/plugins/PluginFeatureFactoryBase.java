package fitnesse.plugins;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fitnesse.Responder;
import fitnesse.authentication.Authenticator;
import fitnesse.components.ComponentFactory;
import fitnesse.components.ComponentInstantiationException;
import fitnesse.responders.editing.ContentFilter;
import fitnesse.testsystems.TestSystemFactory;
import fitnesse.testsystems.slim.CustomComparator;
import fitnesse.testsystems.slim.tables.SlimTable;
import fitnesse.wiki.WikiPageFactory;
import fitnesse.wikitext.parser.SymbolType;

public class PluginFeatureFactoryBase implements PluginFeatureFactory {
  private ComponentFactory componentFactory;

  @Override
  public List<Object> getPlugins() throws PluginException {
    return createList();
  }

  @Override
  public Map<String, Class<? extends Responder>> getResponders() throws PluginException {
      return createMap();
  }

  @Override
  public Authenticator getAuthenticator() {
    return null;
  }

  @Override
  public List<SymbolType> getSymbolTypes() throws PluginException {
    return createList();
  }

  @Override
  public List<WikiPageFactory> getWikiPageFactories() throws PluginException {
    return createList();
  }

  @Override
  public ContentFilter getContentFilter() {
    return null;
  }

  @Override
  public Map<String, Class<? extends SlimTable>> getSlimTables() throws PluginException {
    return createMap();
  }

  @Override
  public Map<String, CustomComparator> getCustomComparators() throws PluginException {
    return createMap();
  }

  @Override
  public Map<String, TestSystemFactory> getTestSystemFactories() throws PluginException {
    return createMap();
  }

  public ComponentFactory getComponentFactory() {
    return componentFactory;
  }

  @Override
  public void setComponentFactory(ComponentFactory componentFactory) {
    this.componentFactory = componentFactory;
  }

  protected <T> T createComponent(Class<T> typeClass) throws PluginException {
    try {
      return componentFactory.createComponent(typeClass);
    } catch (ComponentInstantiationException e) {
      throw new PluginException("Cannot create instance of " + typeClass.getName(), e);
    }
  }

  private <T> Map<String, T> createMap() {
    return new LinkedHashMap<String, T>();
  }

  private <T> List<T> createList() {
    return new ArrayList<T>();
  }
}
