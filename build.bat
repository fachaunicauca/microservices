$services = @(
    "api-gateway",\
    "auth-service",
    "discovery-service",
    "educator-service",
    "inventory-service",
    "test-service"
)

$failedServices = @()

Write-Host "🚀 Generando .jar para todos los servicios..."

foreach ($service in $services) {
    Write-Host "📦 Compilando $service ..."
    Push-Location $service
    mvn clean package -DskipTests
    if ($LASTEXITCODE -eq 0) {
        Write-Host "✅ $service compilado con éxito."
    } else {
        Write-Host "❌ Error al compilar $service"
        $failedServices += $service
    }
    Pop-Location
}

if ($failedServices.Count -eq 0) {
    Write-Host "🎉 Todos los servicios han sido compilados exitosamente."
    exit 0
} else {
    Write-Host "❌ Falló la compilación de los siguientes servicios:"
    foreach ($failed in $failedServices) {
        Write-Host "   - $failed"
    }
    exit 1
}
