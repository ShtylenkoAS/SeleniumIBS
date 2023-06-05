package org.selenium.framework.basetestclass;

import org.selenium.framework.managers.PageManger;
import org.selenium.framework.managers.TestPropManager;

import java.util.Properties;

public class BaseTest {

    protected PageManger manger = PageManger.getPageManger();

    protected Properties properties = TestPropManager.getInstance().getProperties();

}
