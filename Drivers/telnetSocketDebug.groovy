import hubitat.helper.HexUtils
import hubitat.device.HubAction
import hubitat.helper.InterfaceUtils
import hubitat.device.Protocol

metadata {
    definition (
        name: "Telnet/Socket Debug", 
        namespace: "Development", 
        author: "Adam Kempenich") {
        
		capability "Switch"
		capability "Initialize"
        capability "Refresh"
    }
    
    preferences {  
        input "deviceIP", "text", title: "Server", description: "Device IP (e.g. 192.168.1.X)", required: true, defaultValue: "192.168.1.X"
        input "devicePort", "number", title: "Port", description: "Device Port (Default: 5577)", required: true, defaultValue: 5577
		input "telnet", "bool", title: "Telnet - true, socket - false", description: "Use Telnet, or Socket?", required: true, defaultValue: true
    }
}

def on() {
    // Turn on the device

    sendEvent(name: "switch", value: "on")
    log.debug( "Switch set to on" )
	// data varies depending on the device. MagicHome uses 	[0x71, 0x23, 0x0F, 0xA3]
    byte[] data = [0x45, 0x00, 0x00, 0xd3, 0x00, 0x00, 0x40, 0x00, 0x40, 0x06, 0x26, 0x00, 0x0a, 0x00, 0x00, 0x0a, 0x0a, 0x00, 0x00, 0x1c, 0xcd, 0x3c, 0x1a, 0x0c, 0x6c, 0x04, 0x06, 0x53, 0x00, 0x00, 0x26, 0xa6, 0x50, 0x18, 0xff, 0xff, 0xa8, 0x9c, 0x00, 0x00, 0x00, 0x00, 0x55, 0xaa, 0x00, 0x00, 0x00, 0x4c, 0x00, 0x00, 0x00, 0x07, 0x00, 0x00, 0x00, 0x9b, 0x33, 0x2e, 0x31, 0x32, 0x38, 0x64, 0x65, 0x38, 0x32, 0x34, 0x66, 0x64, 0x35, 0x34, 0x35, 0x31, 0x37, 0x62, 0x66, 0x48, 0x69, 0x49, 0x66, 0x35, 0x4d, 0x72, 0x34, 0x74, 0x4a, 0x38, 0x41, 0x30, 0x31, 0x44, 0x55, 0x2b, 0x44, 0x58, 0x48, 0x74, 0x49, 0x59, 0x41, 0x70, 0x6e, 0x67, 0x63, 0x57, 0x76, 0x69, 0x49, 0x52, 0x63, 0x4a, 0x6f, 0x59, 0x66, 0x63, 0x67, 0x43, 0x34, 0x59, 0x55, 0x42, 0x41, 0x65, 0x72, 0x2b, 0x6a, 0x6d, 0x55, 0x50, 0x68, 0x31, 0x4b, 0x56, 0x71, 0x6e, 0x45, 0x2f, 0x56, 0x33, 0x68, 0x73, 0x46, 0x48, 0x37, 0x44, 0x42, 0x69, 0x52, 0x34, 0x79, 0x41, 0x46, 0x30, 0x30, 0x53, 0x4c, 0x6f, 0x67, 0x57, 0x65, 0x33, 0x38, 0x55, 0x78, 0x79, 0x32, 0x77, 0x2b, 0x54, 0x4c, 0x41, 0x71, 0x48, 0x4b, 0x37, 0x4e, 0x72, 0x6a, 0x57, 0x6d, 0x71, 0x43, 0x62, 0x55, 0x67, 0x5a, 0x6d, 0x66, 0x46, 0x66, 0x48, 0x38, 0x62, 0x42, 0x78, 0x53, 0x56, 0x54, 0x2f, 0x4c, 0x31, 0x6a, 0x42, 0x72, 0x89, 0x09, 0x3f, 0xe4, 0x00, 0x00, 0xaa, 0x55]
    sendCommand(data)
}

def off() {
    // Turn off the device

    sendEvent(name: "switch", value: "off")
    log.debug( "Switch set to off" )
	// data varies depending on the device. MagicHome uses [0x71, 0x24, 0x0F, 0xA4]
    byte[] data = [0x45, 0x00, 0x00, 0xeb, 0x00, 0x00, 0x40, 0x00, 0x40, 0x06, 0x25, 0xe8, 0x0a, 0x00, 0x00, 0x0a, 0x0a, 0x00, 0x00, 0x1c, 0xcd, 0x3c, 0x1a, 0x0c, 0x6c, 0x04, 0x05, 0x90, 0x00, 0x00, 0x25, 0xef, 0x50, 0x18, 0xff, 0xff, 0xb0, 0xa7, 0x00, 0x00, 0x00, 0x00, 0x55, 0xaa, 0x00, 0x00, 0x00, 0x4b, 0x00, 0x00, 0x00, 0x07, 0x00, 0x00, 0x00, 0xb3, 0x33, 0x2e, 0x31, 0x32, 0x66, 0x38, 0x31, 0x63, 0x62, 0x64, 0x30, 0x31, 0x64, 0x65, 0x61, 0x37, 0x66, 0x64, 0x33, 0x48, 0x69, 0x49, 0x66, 0x35, 0x4d, 0x72, 0x34, 0x74, 0x4a, 0x38, 0x41, 0x30, 0x31, 0x44, 0x55, 0x2b, 0x44, 0x58, 0x48, 0x74, 0x49, 0x59, 0x41, 0x70, 0x6e, 0x67, 0x63, 0x57, 0x76, 0x69, 0x49, 0x52, 0x63, 0x4a, 0x6f, 0x59, 0x66, 0x63, 0x67, 0x43, 0x34, 0x59, 0x55, 0x42, 0x41, 0x65, 0x72, 0x2b, 0x6a, 0x6d, 0x55, 0x50, 0x68, 0x31, 0x4b, 0x56, 0x71, 0x6e, 0x45, 0x2f, 0x56, 0x33, 0x68, 0x73, 0x46, 0x48, 0x37, 0x44, 0x42, 0x69, 0x52, 0x34, 0x79, 0x41, 0x46, 0x30, 0x30, 0x53, 0x4c, 0x6f, 0x67, 0x57, 0x65, 0x33, 0x37, 0x75, 0x59, 0x6e, 0x42, 0x66, 0x37, 0x2b, 0x73, 0x4c, 0x6d, 0x58, 0x54, 0x6e, 0x47, 0x55, 0x79, 0x6d, 0x32, 0x71, 0x42, 0x6a, 0x66, 0x34, 0x66, 0x4a, 0x62, 0x59, 0x53, 0x4a, 0x36, 0x6a, 0x41, 0x71, 0x4f, 0x76, 0x32, 0x4e, 0x72, 0x71, 0x59, 0x51, 0x70, 0x32, 0x46, 0x56, 0x75, 0x50, 0x77, 0x34, 0x55, 0x44, 0x6d, 0x34, 0x68, 0x50, 0x72, 0x31, 0x57, 0x6a, 0x68, 0x35, 0x6c, 0x47, 0x77, 0x3d, 0x3d, 0xe4, 0xa5, 0x46, 0x61, 0x00, 0x00, 0xaa, 0x55]
    sendCommand(data)
}

def parse( response ) {
    // Parse data received back from this device
	
	unschedule()
	runIn(60, refresh)
	
	def responseArray = HexUtils.hexStringToIntArray(response)	
	log.debug "Parsed ${response}, length is ${responseArray.length}"

}


def sendCommand( data ) {
    // Sends commands to the device
    
	String stringBytes = HexUtils.byteArrayToHexString(data)
    logDebug "${data} was converted. Transmitting: ${stringBytes}"
	if(settings.telnet == false || settings.telnet != null){
		InterfaceUtils.sendSocketMessage(device, stringBytes)
	}
	else{
		def transmission = new HubAction(stringBytes, Protocol.TELNET)
     	sendHubCommand(transmission)
	}
	runIn(60, initialize)
}

def refresh( ) {
    // For magichome:  0x81, 0x8A, 0x8B, 0x96 
    byte[] data =  [0x45, 0x00, 0x00, 0x40, 0x00, 0x00, 0x40, 0x00, 0x40, 0x06, 0x26, 0x93, 0x0a, 0x00, 0x00, 0x0a, 0x0a, 0x00, 0x00, 0x1c, 0xcd, 0x3c, 0x1a, 0x0c, 0x6c, 0x04, 0x05, 0x78, 0x00, 0x00, 0x25, 0xd3, 0x50, 0x18, 0xff, 0xff, 0x4b, 0x7c, 0x00, 0x00, 0x00, 0x00, 0x55, 0xaa, 0x00, 0x00, 0x00, 0x49, 0x00, 0x00, 0x00, 0x09, 0x00, 0x00, 0x00, 0x08, 0x17, 0xa2, 0xb9, 0x7e, 0x00, 0x00, 0xaa, 0x55]
    sendCommand( data )
	
	unschedule()
	runIn(20, refresh)
}

def telnetStatus( status ) { 
	logDebug "telnetStatus: ${status}" 
	logDebug "Attempting to reconnect."
	runIn(2, initialize)
}

def socketStatus( status ) { 
	log.debug "socketStatus: ${status}"
	logDebug "Attempting to reconnect."
	runIn(2, initialize) 
}

def updated(){
    initialize()
}

def initialize() {
    // Establish a connection to the device
    
    log.debug "Initializing device."
	
	try {
	InterfaceUtils.socketClose(device)
	}
	catch(e) {
		log.debug "No socket to close"
	}
	
	telnetClose()
	unschedule()
	try {
		if(settings.telnet == false || settings.telnet != null){
			log.debug "Opening Socket Connection."
			InterfaceUtils.socketConnect(device, settings.deviceIP, settings.devicePort.toInteger(), byteInterface: true)
		}
		else{
			log.debug "Opening TCP-Telnet Connection."
			telnetConnect([byteInterface: true, termChars:[170,85]], "${settings.deviceIP}", settings.devicePort.toInteger(), null, null)
		}
		
		pauseExecution(1000)
		log.debug "Connection successfully established"
	    runIn(2, refresh)
	} catch(e) {
		log.debug("Error attempting to establish TCP-Telnet connection to device.")
		log.debug("Next initialization attempt in ${settings.refreshtime} seconds.")
		sendEvent(name: "switch", value: "off")
		settings.refreshTime == null ? runIn(60, initialize) : runIn(settings.refreshTime, initialize)
	}
}
