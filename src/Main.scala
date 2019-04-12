object Main extends App {
  var _running = true
  Network.display()

  var c_exist = false
  while (_running) { // MAIN APP LOOP
    print("Network ~ ")
    val input = scala.io.StdIn.readLine()

    c_exist = false
    for ( i <- 0 to Network.net.length-1 ) {
      if (Network.net(i) != null) {
        if (input == Network.net(i).name || input == Network.net(i).ip) {
          Network.net(i).terminal()
          c_exist = true
        }
      }
    }
    if (input == "exit" || input == "quit") {
      _running = false
    }
    else if ( input == "display" ) {
      Network.display()
      c_exist = true
    }
    else if ( input == "pc" ) {
      Network.newpc()
      c_exist = true
    }
    else if (!c_exist) {
      println()
      println("Unknown Command ~ " ++ input)
      println("Type ? for a list of compatible commands")
    }
  }
}

object Network {
  val netSize = 9
  val net = new Array[PC](netSize)

  // Default Machines
  net(0) = new PC("Firewall", "192.168.1.1")
  net(1) = new PC("DC", "192.168.1.2")
  net(2) = new PC("Client-01", "192.168.1.101")

  def display(): Unit = {
    println()
    for ( i <- 0 to netSize-1 ) {
      if (net(i) != null) {
        println("Name: " ++ net(i).name ++ "\nIP: " ++ net(i).ip)
        println()
      }
    }
  }
  def newpc(): Unit = {
    println()
    print("Network ~ NewPC ~ Name: ")
    val n = scala.io.StdIn.readLine()

    print("Network ~ NewPC ~ IP: ")
    val i = scala.io.StdIn.readLine()
    var ip_e = false

    var valid_ip = true
    while (!valid_ip) {
      for (j <- 0 to net.length - 1) {
        if (net(j) != null) {
          if (i == net(j).ip) {
            ip_e = true
          }
        }
      }
      if (!ip_e) { valid_ip = false }
      else { println("IP Already Exists ~ " ++ i) }
    }
  }
}

class PC {
  var name = "PC-######"
  var ip = "0.0.0.0"
  def this(n: String, i: String) {
    this()
    name = n
    ip = i
  }

  def terminal(): Unit = {
    var inLoop = true
    while (inLoop) {
      println("Name: " ++ this.name ++ "\nIP: " ++ this.ip)
      print(this.name ++ " > ")
      val input = scala.io.StdIn.readLine()

      if (input == "ip-c") {
        print(this.name ++ " > ip > ")
        val inp = scala.io.StdIn.readLine()
        var ip_e = false
        for ( i <- 0 to Network.net.length-1 ) {
          if ( Network.net(i) != null ) {
            if (inp == ip) {
              ip_e = true
            }
          }
        }
        if (!ip_e) ip = inp
      }

      else if (input == "exit" || input == "quit") {
        inLoop = false
      }
      else {
        println()
        println("Unknown Command ~ " ++ input)
        println("Type ? for a list of compatible commands")
      }
    }
  }
}