package eu.execom.chatapplicationspring2017.eventbus;

import com.squareup.otto.Bus;

import org.androidannotations.annotations.EBean;

@EBean(scope = EBean.Scope.Singleton)
public class OttoBus extends Bus {

}
