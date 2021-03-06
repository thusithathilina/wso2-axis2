/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.axis2.policy.model;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.neethi.Constants;
import org.apache.neethi.PolicyComponent;

/**
 * Assertion to pick up the QName &ltwsoma:MTOM
 * xmlns:wsoma="http://www.w3.org/2007/08/soap12-mtom-policy"/&gt which is compliant </br>
 * with the WS-MTOMPolicy verion 1.1.
 */

public class MTOM11Assertion extends MTOMAssertion {

    // creating a logger instance
    private static Log log = LogFactory.getLog(MTOM11Assertion.class);

    public final static String NS = "http://www.w3.org/2007/08/soap12-mtom-policy";

    public final static String MTOM_LN = "MTOM";

    public final static String PREFIX = "wsoma";

    public QName getName() {
        return new QName(NS, MTOM_LN);
    }

    public PolicyComponent normalize() {
        throw new UnsupportedOperationException("TODO");
    }

    public void serialize(XMLStreamWriter writer) throws XMLStreamException {
        String prefix = writer.getPrefix(NS);

        if (prefix == null) {
            prefix = PREFIX;
            writer.setPrefix(PREFIX, NS);
        }

        writer.writeStartElement(PREFIX, MTOM_LN, NS);

        if (optional) {
            writer.writeAttribute(Constants.ATTR_WSP, null,
                    Constants.Q_ELEM_OPTIONAL_ATTR.getLocalPart(), "true");
        }

        writer.writeNamespace(PREFIX, NS);
        writer.writeEndElement();

    }

    public boolean equal(PolicyComponent policyComponent) {
        throw new UnsupportedOperationException("TODO");
    }

    public short getType() {
        return Constants.TYPE_ASSERTION;
    }

}
