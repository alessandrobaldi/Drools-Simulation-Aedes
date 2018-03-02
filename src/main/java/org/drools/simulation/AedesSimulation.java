/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.simulation;

import org.drools.simulation.Scenary;
import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.drools.core.time.SessionPseudoClock;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;

/**
 * This is a sample file to launch a rule package from a rule source file.
 */
public class AedesSimulation {

    public static final void main(final String[] args) throws InterruptedException {
        // KieServices is the factory for all KIE services 
        Date refDate = new Date(System.currentTimeMillis());
    boolean realMode = false;
    SessionPseudoClock clock = null;
        
        
        KieServices ks = KieServices.Factory.get();
       
        
        
        KieSessionConfiguration config = KieServices.Factory.get().newKieSessionConfiguration();
    if(realMode) {
        config.setOption( ClockTypeOption.get("realtime") );                        
    } else {
        config.setOption( ClockTypeOption.get("pseudo") );  
    }

        
        // From the kie services, a container is created from the classpath
        KieContainer kc = ks.getKieClasspathContainer();
KieSession ksession = kc.newKieSession("Aedes",config);
       Scenary ufes = new Scenary("c0,c1,c2,c3,c4,c5,c6,c7,c8,c9","c0-c1,c1-c2,c2-c3,c3-c4,c4-c5,c5-c6,c6-c7,c7-c8,c8-c9","","c0,c1,c2,c3,c4,c5,c6,c7,c8,c9","c0",90);
        ksession.insert(ufes);
        ksession.fireAllRules();
        clock = ksession.getSessionClock();

       while(true)
       {
           clock.advanceTime( 10, TimeUnit.DAYS );
           Thread.sleep(3000);
           System.out.println("Avançou");
           
       }

    }
}
/*
    public static void execute( KieContainer kc ) {
        // From the container, a session is created based on
        // its definition and configuration in the META-INF/kmodule.xml file
        


        // Once the session is created, the application can interact with it
        // In this case it is setting a global as defined in the
        // org/drools/examples/helloworld/HelloWorld.drl file
        //ksession.setGlobal( "list",
                            //new ArrayList<Object>() );

        // The application can also setup listeners
        //ksession.addEventListener( new DebugAgendaEventListener() );
        //ksession.addEventListener( new DebugRuleRuntimeEventListener() );

        // To setup a file based audit logger, uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newFileLogger( ksession, "./helloworld" );

        // To setup a ThreadedFileLogger, so that the audit view reflects events whilst debugging,
        // uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger( ksession, "./helloworld", 1000 );

        // The application can insert facts into the session
        
        Scenary ufes = new Scenary("c0,c1,c2,c3,c4,c5,c6,c7,c8,c9","c0-c1,c1-c2,c2-c3,c3-c4,c4-c5,c5-c6,c6-c7,c7-c8,c8-c9","","c0,c1,c2,c3,c4,c5,c6,c7,c8,c9","c0",90);
        //Scenary = declaração de casas, declaração de ligações entre casas, declaração de casas com armadilha, declaração de casas com foco, declaração de casas com mosquito
        //final Message message = new Message();
        //message.setMessage( "Hello World" );
        //message.setStatus( Message.HELLO );
        ksession.insert(ufes);
        // and fire the rules
        long start = System.currentTimeMillis();
        ksession.fireAllRules();
long elapsed = System.currentTimeMillis() - start;
        System.out.println("Time:"+elapsed);
        // Remove comment if using logging
        // logger.close();

        // and then dispose the session
        ksession.dispose();
    }

    

}*/
