package org.dmnl.dmsngr.configurations;

import org.dmnl.dmsngr.repositories.MessageDaoImpl;
import org.dmnl.dmsngr.repositories.MessageDao;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ApplicationAbstractBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(MessageDaoImpl.class).to(MessageDao.class);
    }

}
