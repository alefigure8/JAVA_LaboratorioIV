	<!-- ESTILOS CSS -->
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
                  <h3 class="fs-5">Confirmar</h3>
                  <p><%=request.getParameter("mensaje")%></p>
                  <div class="d-flex gap-4 justify-content-center">
                      <button id="btnAceptar" class="btn btn-main bg-primary text-white">Aceptar</button>
                      <button id="cerrarPopup" class="btn btn-main bg-danger text-white">Cancelar</button>
                  </div>
              </div>
          </div>
      </div>

	<!-- SCRIPT JS -->
    <script>
        var popup = document.getElementById("popup");
        var cerrarBoton = document.getElementById("cerrarPopup");

        popup.style.display = "block";

        popup.onclick = function(e) {
            if (e.target === popup) {
                popup.style.display = "none";
            }
        };

        cerrarBoton.onclick = function() {
            popup.style.display = "none";
        };
    </script>