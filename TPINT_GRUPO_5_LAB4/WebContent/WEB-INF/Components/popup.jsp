<style>
      .popup-background {
	          position: fixed;
	          top: 0;
	          left: 0;
	          width: 100%;
	          height: 100%;
	          background-color: rgba(0, 0, 0, 0.2);
	          display: flex;
	          justify-content: center;
	          align-items: center;
	          cursor: pointer;
          
      }

      .popup-container {
			position: relative;
			top: 40%;
			width: 300px;
			background-color: #fff;
			padding: 20px;
			border-radius: 5px;
			box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
			text-align: center;
			margin: 0 auto; 
      }

      .close-button {
			cursor: pointer;
			position: absolute;
			top: 10px;
			right: 10px;
      }
  </style>

	<!-- POPUP -->
    <div class="d-flex">
	    <div id="popup" class="popup-background">
	        <div class="popup-container">
	            <h3 class="fs-5"><%=request.getParameter("titulo")%></h3>
	            <p><%=request.getParameter("mensaje")%></p>
	            <!-- Bot�n de cierre -->
	            <span id="cerrarPopup" class="close-button fs-5">&times;</span>
	        </div>
	    </div>
    </div>

    <script>
        var popup = document.getElementById("popup");
        var cerrarBoton = document.getElementById("cerrarPopup");

        popup.style.display = "block";

        popup.onclick = function(event) {
            if (event.target === popup) {
                popup.style.display = "none";
            }
        };

        cerrarBoton.onclick = function() {
            popup.style.display = "none";
        };
    </script>