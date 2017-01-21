#!/usr/bin/env python3
#
# This is a NetworkTables client (eg, the DriverStation/coprocessor side).
# You need to tell it the IP address of the NetworkTables server (the
# robot or simulator).
#
# When running, this will continue incrementing the value 'dsTime', and the
# value should be visible to other networktables clients and the robot.
#

import sys
import time
from networktables import NetworkTables

# To see messages from networktables, you must setup logging
import logging
logging.basicConfig(level=logging.DEBUG)

NetworkTables.initialize(server='roborio-2761-frc.local')

table = NetworkTables.getTable("ImageData")

i = 0
while True:
    try:
        print('dsTime:', table.getNumber('dsTime'))
    except KeyError:
        print('dsTime: N/A')

    table.putNumber('dsTime', i)
    time.sleep(1)
    i += 1
