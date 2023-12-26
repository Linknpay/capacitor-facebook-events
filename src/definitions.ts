export interface FacebookEventsPlugin {
  setAdvertiserTrackingEnabled(options: { enabled: boolean }): Promise<void>;
  logEvent(options: { event: string; params?: any }): Promise<void>;
}
