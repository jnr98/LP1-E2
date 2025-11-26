## 📌 Projeto Eagles - ViagensFX
Sistema desktop desenvolvido em Java + JavaFX + MySQL para gerenciamento de registros relacionados a viagens, envolvendo prisioneiros, passaportes e rodovias. O sistema permite realizar operações completas de cadastro, edição, listagem e exclusão de dados, com interface gráfica intuitiva.</p>

---

## 🛠 Tecnologias Utilizadas:
<div align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="40" alt="java logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" height="40" alt="mysql logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/intellij/intellij-original.svg" height="40" alt="intellij logo"  />
</div>

###

---

## 📂 Estrutura do Projeto

```
Eagles
│
├── src
│   ├── controller
│   │   └── MainController.java
│   ├── dao
│   │   ├── PrisoneiroDAO.java
│   │   ├── PassaporteDAO.java
│   │   └── RodoviaDAO.java
│   ├── model
│   │   ├── Prisoneiro.java
│   │   ├── Passaporte.java
│   │   └── Rodovia.java
│   ├── util
│   │   └── ConnectionFactory.java
│   └── view
│       └── MainView.fxml
```

---

## 🧱 Arquitetura e Estrutura do Projeto

O projeto segue uma organização em camadas:

- **model** → classes de domínio (entidades do sistema)  
- **dao** → classes responsáveis pelo acesso ao banco de dados  
- **controller** → lógica da interface e regras de negócio  
- **view** → arquivos FXML e componentes visuais  
- **util** → utilitários, como a `ConnectionFactory`  


---
## Script de criação do Banco
![alt text](image.png)

---