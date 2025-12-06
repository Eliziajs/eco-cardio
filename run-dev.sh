#!/bin/bash

# run-dev-quarkus.sh - Script 100% funcional para Quarkus

set -e

# Cores
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

echo_color() {
    echo -e "${2}${1}${NC}"
}

# ==============================
# FUNÇÃO PARA INSTALAR QUARKUS CLI DIRETO
# ==============================

install_quarkus_direct() {
    echo_color "📦 Instalando Quarkus CLI via método direto..." "$YELLOW"

    # Método 1: Instala JBang primeiro (mais confiável)
    echo_color "   Passo 1: Instalando JBang..." "$YELLOW"

    # Cria diretório temporário
    TEMP_DIR=$(mktemp -d)
    cd "$TEMP_DIR"

    # Baixa e instala JBang
    curl -Ls https://github.com/jbangdev/jbang/releases/download/v0.114.0/jbang-0.114.0.zip -o jbang.zip
    unzip -q jbang.zip
    rm jbang.zip

    # Move para um local no PATH
    if [[ "$OSTYPE" == "msys" ]] || [[ "$OSTYPE" == "cygwin" ]]; then
        # Windows (Git Bash)
        mkdir -p ~/bin
        cp -r jbang-*/* ~/bin/
        echo 'export PATH="$HOME/bin:$PATH"' >> ~/.bashrc
        source ~/.bashrc
    else
        # Linux/Mac
        sudo mv jbang-*/bin/jbang /usr/local/bin/
        sudo chmod +x /usr/local/bin/jbang
    fi

    # Limpa
    cd -
    rm -rf "$TEMP_DIR"

    # Verifica se jbang foi instalado
    if ! command -v jbang &> /dev/null; then
        echo_color "   ❌ Falha ao instalar JBang" "$RED"
        return 1
    fi

    echo_color "   ✅ JBang instalado" "$GREEN"

    # Método 2: Instala Quarkus CLI via JBang
    echo_color "   Passo 2: Instalando Quarkus CLI..." "$YELLOW"

    # Usa jbang para instalar quarkus
    jbang app install --fresh --force quarkus@quarkusio 2>&1 | while read line; do
        echo "   $line"
    done

    # Verifica se quarkus foi instalado
    if command -v quarkus &> /dev/null; then
        echo_color "   ✅ Quarkus CLI instalada com sucesso!" "$GREEN"
        return 0
    else
        # Tenta encontrar no caminho alternativo
        if [ -f ~/.jbang/bin/quarkus ]; then
            echo 'export PATH="$HOME/.jbang/bin:$PATH"' >> ~/.bashrc
            source ~/.bashrc
            echo_color "   ✅ Quarkus CLI encontrada em ~/.jbang/bin" "$GREEN"
            return 0
        fi
    fi

    return 1
}

# ==============================
# INSTALAÇÃO RÁPIDA (alternativa)
# ==============================

install_quarkus_quick() {
    echo_color "⚡ Instalando Quarkus CLI (método rápido)..." "$YELLOW"

    # Método direto sem JBang (usando curl + bash)
    curl -Ls https://sh.jbang.dev | bash -s - app setup 2>&1 | grep -v "SDKMAN" || true

    # Espera um pouco
    sleep 2

    # Tenta instalar quarkus
    ~/.jbang/bin/jbang app install quarkus@quarkusio 2>&1 | grep -v "SDKMAN" || true

    # Adiciona ao PATH
    if [ -f ~/.jbang/bin/quarkus ]; then
        echo 'export PATH="$HOME/.jbang/bin:$PATH"' >> ~/.bashrc
        export PATH="$HOME/.jbang/bin:$PATH"
        echo_color "   ✅ Quarkus CLI instalada em ~/.jbang/bin" "$GREEN"
        return 0
    fi

    return 1
}

# ==============================
# VERIFICAÇÕES
# ==============================

check_docker() {
    if docker version >/dev/null 2>&1; then
        return 0
    else
        return 1
    fi
}

check_quarkus() {
    if command -v quarkus &> /dev/null; then
        return 0
    elif [ -f ~/.jbang/bin/quarkus ]; then
        export PATH="$HOME/.jbang/bin:$PATH"
        return 0
    else
        return 1
    fi
}

# ==============================
# PROGRAMA PRINCIPAL
# ==============================

clear
echo_color "╔══════════════════════════════════════════════╗" "$BLUE"
echo_color "║     AMBIENTE QUARKUS - INSTALAÇÃO FÁCIL     ║" "$BLUE"
echo_color "╚══════════════════════════════════════════════╝" "$BLUE"
echo ""

# ===== 1. DOCKER =====
echo_color "1. VERIFICANDO DOCKER..." "$BLUE"

if check_docker; then
    echo_color "   ✅ Docker está funcionando" "$GREEN"
    echo "   Versão: $(docker --version | cut -d' ' -f3-5)"
else
    echo_color "   ❌ Docker não está disponível" "$RED"
    echo ""
    echo "   Como resolver:"
    echo "   1. Abra o Docker Desktop"
    echo "   2. Aguarde até o ícone ficar verde"
    echo "   3. Volte aqui e pressione Enter"
    echo ""
    read -p "   Pressione Enter quando Docker estiver rodando... " -n 1 -r

    if ! check_docker; then
        echo_color "   ❌ Docker ainda não está funcionando" "$RED"
        echo_color "   Execute manualmente e tente novamente" "$YELLOW"
        exit 1
    fi
fi

# ===== 2. QUARKUS CLI =====
echo ""
echo_color "2. VERIFICANDO QUARKUS CLI..." "$BLUE"

if check_quarkus; then
    echo_color "   ✅ Quarkus CLI já está instalada" "$GREEN"
    quarkus --version 2>/dev/null || echo "   (versão disponível)"
else
    echo_color "   ⚠ Quarkus CLI não encontrada" "$YELLOW"
    echo ""

    # Oferece opções de instalação
    echo "   Escolha como instalar:"
    echo "   1. Instalação automática (recomendado)"
    echo "   2. Instalação manual"
    echo "   3. Usar Maven/Gradle (sem CLI)"
    echo ""
    read -p "   Digite sua escolha (1-3): " choice

    case $choice in
        1)
            echo ""
            if install_quarkus_direct; then
                echo_color "   ✅ Quarkus CLI instalada com sucesso!" "$GREEN"
            elif install_quarkus_quick; then
                echo_color "   ✅ Quarkus CLI instalada via método rápido!" "$GREEN"
            else
                echo_color "   ❌ Não foi possível instalar automaticamente" "$RED"
                echo_color "   Tente a instalação manual" "$YELLOW"
            fi
            ;;
        2)
            echo ""
            echo_color "   Para instalar manualmente:" "$YELLOW"
            echo "   1. Feche este terminal"
            echo "   2. Abra um NOVO terminal como Administrador"
            echo "   3. Execute:"
            echo "      curl -Ls https://sh.jbang.dev | bash -s - app install quarkus@quarkusio"
            echo "   4. Feche e abra o terminal novamente"
            echo "   5. Execute este script outra vez"
            echo ""
            read -p "   Pressione Enter para continuar sem Quarkus CLI... " -n 1 -r
            ;;
        3)
            echo ""
            echo_color "   Usando Maven/Gradle..." "$YELLOW"
            echo "   Você pode usar:"
            echo "   - Maven: ./mvnw quarkus:dev"
            echo "   - Gradle: ./gradlew quarkusDev"
            echo ""
            ;;
    esac
fi

# ===== 3. INICIAR CONTAINERS =====
echo ""
echo_color "3. INICIANDO CONTAINERS..." "$BLUE"

# Configurações do seu docker-compose
echo_color "   Iniciando PostgreSQL, SonarQube, etc..." "$YELLOW"

if docker-compose up -d; then
    echo_color "   ✅ Containers iniciados com sucesso" "$GREEN"
else
    echo_color "   ❌ Erro ao iniciar containers" "$RED"
    echo_color "   Execute: docker-compose logs para ver detalhes" "$YELLOW"
    exit 1
fi

# Aguarda um pouco
sleep 5

# ===== 4. VERIFICAR POSTGRESQL =====
echo ""
echo_color "4. VERIFICANDO BANCO DE DADOS..." "$BLUE"

echo_color "   Aguardando PostgreSQL na porta 5433..." "$YELLOW"

for i in {1..20}; do
    if docker-compose exec -T postgres-eco pg_isready -U postgres -d meu_banco >/dev/null 2>&1; then
        echo_color "   ✅ PostgreSQL pronto!" "$GREEN"
        break
    fi
    echo -n "."
    sleep 2
done

# ===== 5. MOSTRAR STATUS =====
echo ""
echo_color "5. STATUS DOS SERVIÇOS:" "$BLUE"
docker-compose ps

# ===== 6. URLs E OPÇÕES =====
echo ""
echo_color "🌐 URLs PARA DESENVOLVIMENTO:" "$GREEN"
echo "   📍 Quarkus App:    http://localhost:8080"
echo "   🔧 Dev UI:         http://localhost:8080/q/dev"
echo "   📝 Swagger UI:     http://localhost:8080/q/swagger-ui"
echo ""
echo "   🗄️  PostgreSQL:    localhost:5433"
echo "                     Usuário: postgres"
echo "                     Senha: minha_senha_segura"
echo "                     Banco: meu_banco"
echo ""
echo "   📊 SonarQube:      http://localhost:9000 (admin/admin)"
echo "   📈 Grafana:        http://localhost:3001 (admin/admin)"
echo "   📉 Prometheus:     http://localhost:9090"

# ===== 7. ESCOLHA DE EXECUÇÃO =====
echo ""
echo_color "══════════════════════════════════════════════" "$BLUE"
echo ""

if check_quarkus; then
    echo_color "🎯 QUARKUS CLI DISPONÍVEL" "$GREEN"
    echo ""
    read -p "Iniciar Quarkus em modo desenvolvimento? (s/N): " -n 1 -r
    echo ""

    if [[ $REPLY =~ ^[Ss]$ ]]; then
        echo_color "🚀 INICIANDO QUARKUS DEV MODE..." "$GREEN"
        echo ""
        echo_color "🔥 Hot reload ativado!" "$YELLOW"
        echo_color "✏️  Modifique seu código e veja as mudanças automaticamente" "$YELLOW"
        echo ""
        echo_color "⏹️  Pressione CTRL+C para parar tudo" "$RED"
        echo ""

        # Configura ambiente
        export QUARKUS_DATASOURCE_JDBC_URL="jdbc:postgresql://localhost:5433/meu_banco"
        export QUARKUS_DATASOURCE_USERNAME="postgres"
        export QUARKUS_DATASOURCE_PASSWORD="minha_senha_segura"

        # Inicia Quarkus
        quarkus dev
    else
        echo_color "Para iniciar Quarkus manualmente:" "$BLUE"
        echo "   Execute: quarkus dev"
        echo ""
        echo_color "Para parar os containers:" "$RED"
        echo "   Execute: docker-compose down"
    fi
else
    # Opções sem Quarkus CLI
    echo_color "⚙️  OPÇÕES SEM QUARKUS CLI:" "$YELLOW"
    echo ""

    if [ -f "pom.xml" ]; then
        read -p "Usar Maven para iniciar? (s/N): " -n 1 -r
        echo ""
        if [[ $REPLY =~ ^[Ss]$ ]]; then
            echo_color "🚀 Iniciando com Maven..." "$GREEN"
            ./mvnw quarkus:dev
        fi
    elif [ -f "gradlew" ]; then
        read -p "Usar Gradle para iniciar? (s/N): " -n 1 -r
        echo ""
        if [[ $REPLY =~ ^[Ss]$ ]]; then
            echo_color "🚀 Iniciando com Gradle..." "$GREEN"
            ./gradlew quarkusDev
        fi
    else
        echo_color "⚠ Nenhum build tool encontrado" "$YELLOW"
        echo ""
        echo_color "Instale o Quarkus CLI manualmente:" "$BLUE"
        echo "   1. Feche todos os terminais"
        echo "   2. Abra um NOVO terminal como Administrador"
        echo "   3. Execute:"
        echo "      curl -Ls https://sh.jbang.dev | bash -s - app install quarkus@quarkusio"
        echo "   4. Feche e abra o terminal novamente"
    fi
fi

# Limpeza
cleanup() {
    echo ""
    echo_color "🛑 Parando containers Docker..." "$RED"
    docker-compose down
    echo_color "✅ Ambiente parado" "$GREEN"
    exit 0
}

if [[ $REPLY =~ ^[Ss]$ ]] || [ -n "$(jobs -p)" ]; then
    trap cleanup SIGINT SIGTERM
fi