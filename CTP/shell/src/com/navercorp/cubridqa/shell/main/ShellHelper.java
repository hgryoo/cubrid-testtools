/**
 * Copyright (c) 2016, Search Solution Corporation. All rights reserved.
 *
 * <p>Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * <p>* Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 *
 * <p>* Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided with
 * the distribution.
 *
 * <p>* Neither the name of the copyright holder nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written permission.
 *
 * <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.navercorp.cubridqa.shell.main;

import com.jcraft.jsch.JSchException;
import com.navercorp.cubridqa.common.ConfigParameterConstants;
import com.navercorp.cubridqa.shell.common.SSHConnect;

public class ShellHelper {

    public static final String getTestNodeTitle(Context context, String envId) {
        String host =
                context.getInstanceProperty(
                        envId, ConfigParameterConstants.TEST_INSTANCE_HOST_SUFFIX);
        return getTestNodeTitle(context, envId, host);
    }

    public static final String getTestNodeTitle(Context context, String envId, String host) {
        String title;
        if (context.isExecuteAtLocal()) {
            title = "local";
        } else {
            String port =
                    context.getInstanceProperty(
                            envId, ConfigParameterConstants.TEST_INSTANCE_PORT_SUFFIX);
            String user =
                    context.getInstanceProperty(
                            envId, ConfigParameterConstants.TEST_INSTANCE_USER_SUFFIX);

            title = user + "@" + host + ":" + port;
        }

        return title;
    }

    public static final SSHConnect createFirstTestNodeConnect(Context context)
            throws JSchException {
        String envId = context.getEnvList().get(0);
        return createTestNodeConnect(context, envId);
    }

    public static final SSHConnect createTestNodeConnect(Context context, String envId)
            throws JSchException {
        String host =
                context.getInstanceProperty(
                        envId, ConfigParameterConstants.TEST_INSTANCE_HOST_SUFFIX);
        return createTestNodeConnect(context, envId, host);
    }

    public static final SSHConnect createTestNodeConnect(Context context, String envId, String host)
            throws JSchException {
        SSHConnect ssh;
        if (context.isExecuteAtLocal()) {
            ssh = new SSHConnect();
        } else {
            String port =
                    context.getInstanceProperty(
                            envId, ConfigParameterConstants.TEST_INSTANCE_PORT_SUFFIX);
            String user =
                    context.getInstanceProperty(
                            envId, ConfigParameterConstants.TEST_INSTANCE_USER_SUFFIX);
            String pwd =
                    context.getInstanceProperty(
                            envId, ConfigParameterConstants.TEST_INSTANCE_PASSWORD_SUFFIX);

            ssh = new SSHConnect(host, port, user, pwd, context.getServiceProtocolType());
        }
        return ssh;
    }
}
